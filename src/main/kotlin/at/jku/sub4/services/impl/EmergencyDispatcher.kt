package at.jku.sub4.services.impl

import at.jku.sub4.models.Entry
import at.jku.sub4.services.DispatcherService
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class EmergencyDispatcher(
    private val calendarServiceImpl: CalendarServiceImpl
): DispatcherService {

    companion object {
        private val LOG = KotlinLogging.logger {}
    }

    override fun dispatch(entry: Entry) {
        LOG.info("New Emergency reported! $entry")
        calendarServiceImpl.createSchedule(entry)
    }
}
