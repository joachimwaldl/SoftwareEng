# temp container to build using gradle
FROM gradle:6.8.0-jdk11 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle.kts settings.gradle.kts $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

COPY . .
RUN gradle build

# actual container
FROM adoptopenjdk/openjdk11:alpine-jre
ENV ARTIFACT_NAME=city-traffic-control-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

RUN apk --no-cache add curl
RUN apk --no-cache add jq

WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

EXPOSE 8084

HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
    CMD curl --silent --fail --request GET http://localhost:8084/actuator/health \
        | jq --exit-status '.status == "UP"' || exit 1

ENTRYPOINT exec java -jar ${ARTIFACT_NAME}