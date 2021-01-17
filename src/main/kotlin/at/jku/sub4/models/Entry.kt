package at.jku.sub4.models

import java.util.*

open class Entry(
        open val date: Date,
        open val location: Location
) {
        override fun toString(): String {
                return "$date at Location={ long: ${location.longitude}, lat: ${location.latitude}}"
        }
}