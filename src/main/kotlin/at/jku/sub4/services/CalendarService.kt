package at.jku.sub4.services

import at.jku.sub4.models.Entry

interface CalendarService {
    fun createSchedule(entry: Entry)
    fun updateSchedule()
    fun displayEntries()
    fun getNextEntry()
}
