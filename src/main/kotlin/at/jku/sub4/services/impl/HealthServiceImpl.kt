package at.jku.sub4.services.impl

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthContributor
import org.springframework.boot.actuate.health.HealthIndicator

class HealthServiceImpl: HealthIndicator, HealthContributor {

    override fun health(): Health {
        return if (healthCheck()) {
            Health.up().build()
        } else {
            Health.down().build()
        }
    }

    private fun healthCheck(): Boolean {
        return true
    }

}