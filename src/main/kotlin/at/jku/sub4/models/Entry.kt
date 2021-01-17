package at.jku.sub4.models

import java.util.*

open class Entry(
        open val date: Date,
        open val location: Location,
        open val entryType: EntryType
) {
        override fun toString(): String {
                return "[$date] $entryType at Location={ long: ${location.longitude}, lat: ${location.latitude}}"
        }
}