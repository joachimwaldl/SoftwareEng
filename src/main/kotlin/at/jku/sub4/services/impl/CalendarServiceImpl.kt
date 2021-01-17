package at.jku.sub4.services.impl

import at.jku.sub4.models.Entry
import at.jku.sub4.services.CalendarService
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class CalendarServiceImpl: CalendarService {
    companion object {
        const val monthYearFormat = "MMM yyyy"
        const val dateFormat = "dd-MM-yyyy"
        const val dateTimeFormat = "dd-MM-yyyy hh:mm:ss"
        const val EXTRA_MARGIN = 15
        const val POST_TIME: Long = 100
    }

    private var entries: ArrayList<Entry> = ArrayList()
    private var calender: Calendar = Calendar.getInstance()
    private var monthTitle: String = "Test"

    fun nextMonth() {
        calender.add(Calendar.MONTH, 1)
        monthTitle = SimpleDateFormat(monthYearFormat, Locale.getDefault()).format(calender.time)
        updateSchedule()
    }

    fun previousMonth() {
        calender.add(Calendar.MONTH, -1)
        monthTitle = SimpleDateFormat(monthYearFormat, Locale.getDefault()).format(calender.time)
        updateSchedule()
    }

    override fun createSchedule(entry: Entry) {
        TODO("Not yet implemented")
    }

    override fun updateSchedule() {
        var selectedCalender: Calendar = Calendar.getInstance()
        selectedCalender.set(Calendar.DATE, 1)
        selectedCalender.set(Calendar.HOUR, 0)
        selectedCalender.set(Calendar.MINUTE, 0)
        selectedCalender.set(Calendar.SECOND, 0)
        calender.set(Calendar.DATE, 1)
        var totalDaysToShow: Int = calender.getActualMaximum(Calendar.DAY_OF_MONTH)
        val toFillInPreviousMonthDays = 1 - calender.get(Calendar.DAY_OF_WEEK)

        calender.set(Calendar.DATE, totalDaysToShow)

        val toFillInNextMonthDays = 7 - calender.get(Calendar.DAY_OF_WEEK)
        totalDaysToShow += Math.abs(toFillInPreviousMonthDays) + toFillInNextMonthDays

        calender.set(Calendar.DATE, 1)

        if (toFillInPreviousMonthDays != 0) {
            selectedCalender.add(Calendar.DAY_OF_YEAR, toFillInPreviousMonthDays)
        }
    }

    override fun displayEntries() {
        TODO("Not yet implemented")
    }

    override fun getNextEntry() {
        TODO("Not yet implemented")
    }
}
