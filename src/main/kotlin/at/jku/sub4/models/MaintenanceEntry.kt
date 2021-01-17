package at.jku.sub4.models

import java.util.*

data class MaintenanceEntry(
        val quality_status: String,
        override val date: Date,
        override val location: Location,
        override val entryType: EntryType
) : Entry(date, location, entryType){

        fun createMaintenanceEntry(quality_status: String, startDate: Date, loc: Location) {
                val entry = MaintenanceEntry(quality_status, startDate, loc, EntryType.MAINTENANCE)
        }

}
