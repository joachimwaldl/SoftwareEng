package at.jku.sub4.services.impl

import at.jku.sub4.models.Entry
import at.jku.sub4.services.DispatcherService
import org.springframework.stereotype.Service

@Service
class EmergencyDispatcher(
    private val calendarServiceImpl: CalendarServiceImpl
): DispatcherService {

    override fun dispatch(entry: Entry) {
        println("New Emergency reported! $entry")
        calendarServiceImpl.createSchedule(entry)
    }
}
