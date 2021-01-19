package at.jku.sub4.tests

import at.jku.sub4.models.Entry
import at.jku.sub4.models.EntryType
import at.jku.sub4.models.Location
import at.jku.sub4.models.MaintenanceEntry
import at.jku.sub4.services.impl.CalendarServiceImpl
import at.jku.sub4.services.impl.CleanupDispatcher
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.NoSuchElementException

internal class SSIVTests {

    @Test
    fun testAddToSchedule() {
        val testEntry = Entry(Date(2020, 12, 12), Location(10.0, 10.0), EntryType.CLEANUP)
        val calendar = CalendarServiceImpl()

        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            calendar.getNextEntry()
        }
        Assertions.assertEquals("Collection is empty.", exception.message)

        calendar.createSchedule(testEntry)

        Assertions.assertEquals(testEntry, calendar.getNextEntry(), "Entry successfully added to Calendar")
    }

    @Test
    fun testAddEntry() {
        val testEntry = Entry(Date(2020, 12, 12), Location(10.0, 10.0), EntryType.CLEANUP)

        Assertions.assertEquals(Location(10.0, 10.0), testEntry.location)
        Assertions.assertFalse(testEntry.entryType.equals(EntryType.EMERGENCY))
        Assertions.assertTrue(testEntry.entryType.equals(EntryType.CLEANUP))
    }

    @Test
    fun testAddMaintenanceEntry() {
        val quality = "bad"
        val start = Date(2020, 12, 12)
        val loc = Location(10.0, 10.0)
        val testEntry = MaintenanceEntry(quality, start, loc, EntryType.MAINTENANCE)

        Assertions.assertTrue(testEntry.quality_status.equals("bad"))
        Assertions.assertTrue(testEntry.entryType.equals(EntryType.MAINTENANCE))
    }

    @Test
    fun testDispatcher() {
        val start = Date(2020, 12, 12)
        val loc = Location(10.0, 10.0)
        val testEntry = Entry(start, loc, EntryType.CLEANUP)
        val calendar = CalendarServiceImpl()
        val dispatcher = CleanupDispatcher(calendar)

        dispatcher.dispatch(testEntry)

        Assertions.assertEquals(testEntry, calendar.getNextEntry(), "Entry successfully added to Calendar")
    }
}