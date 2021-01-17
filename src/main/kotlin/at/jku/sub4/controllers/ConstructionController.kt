package at.jku.sub4.controllers

import at.jku.sub4.models.Entry
import at.jku.sub4.models.Location
import at.jku.sub4.services.impl.CleanupDispatcher
import at.jku.sub4.services.impl.EmergencyDispatcher
import at.jku.sub4.services.impl.SafetyCheckDispatcher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/construction")
class ConstructionController(
    private val cleanupService: CleanupDispatcher,
    private val emergencyDispatcher: EmergencyDispatcher,
    private val safetyCheckDispatcher: SafetyCheckDispatcher
) {

    @PostMapping("/cleanup")
    fun reportCleanup(@RequestBody location: Location): ResponseEntity<Void> {
        val entry = Entry(Date(), location)
        cleanupService.dispatch(entry)

        return ResponseEntity.ok().build()
    }

    @PostMapping("/emergency")
    fun reportEmergency(@RequestBody location: Location): ResponseEntity<Void> {
        val entry = Entry(Date(), location)
        emergencyDispatcher.dispatch(entry)

        return ResponseEntity.ok().build()
    }

    @PostMapping("/safety-check")
    fun reportSafetyCheck(@RequestBody location: Location): ResponseEntity<Void> {
        val entry = Entry(Date(), location)
        safetyCheckDispatcher.dispatch(entry)

        return ResponseEntity.ok().build()
    }

}