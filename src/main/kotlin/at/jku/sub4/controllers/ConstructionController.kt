package at.jku.sub4.controllers

import at.jku.sub4.models.Entry
import at.jku.sub4.models.EntryType
import at.jku.sub4.models.Location
import at.jku.sub4.services.impl.CalendarServiceImpl
import at.jku.sub4.services.impl.CleanupDispatcher
import at.jku.sub4.services.impl.EmergencyDispatcher
import at.jku.sub4.services.impl.SafetyCheckDispatcher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/construction")
class ConstructionController(
    private val cleanupService: CleanupDispatcher,
    private val emergencyDispatcher: EmergencyDispatcher,
    private val safetyCheckDispatcher: SafetyCheckDispatcher,
    private val calendarServiceImpl: CalendarServiceImpl
) {

    @GetMapping
    fun getEntries(): ResponseEntity<List<Entry>> {
        return ResponseEntity.ok(calendarServiceImpl.entries)
    }

    @PostMapping("/cleanup")
    fun reportCleanup(@RequestBody location: Location): ResponseEntity<Void> {
        val entry = Entry(Date(), location, EntryType.CLEANUP)
        cleanupService.dispatch(entry)

        return ResponseEntity.ok().build()
    }

    @PostMapping("/emergency")
    fun reportEmergency(@RequestBody location: Location): ResponseEntity<Void> {
        val entry = Entry(Date(), location, EntryType.EMERGENCY)
        emergencyDispatcher.dispatch(entry)

        return ResponseEntity.ok().build()
    }

    @PostMapping("/safety-check")
    fun reportSafetyCheck(@RequestBody location: Location): ResponseEntity<Void> {
        val entry = Entry(Date(), location, EntryType.SAFETYCHECK)
        safetyCheckDispatcher.dispatch(entry)

        return ResponseEntity.ok().build()
    }

}