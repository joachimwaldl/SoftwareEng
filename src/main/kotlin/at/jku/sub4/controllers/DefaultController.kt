package at.jku.sub4.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class DefaultController {

    @GetMapping
    fun defaultGet(): ResponseEntity<Void> {
        return ResponseEntity.ok().build()
    }
}