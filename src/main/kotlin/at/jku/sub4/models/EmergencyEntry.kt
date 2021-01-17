package at.jku.sub4.models

import java.util.*

data class EmergencyEntry(
        val time_limit: Long,
        override val date: Date,
        override val location: Location,
        override val entryType: EntryType
) : Entry(date, location, entryType) {

        fun createEmergencyEntry(time_limit: Long, startDate: Date, loc: Location) {
                val entry = EmergencyEntry(time_limit, startDate, loc, EntryType.EMERGENCY)
        }
}