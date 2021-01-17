package at.jku.sub4.services

import at.jku.sub4.models.Entry

interface DispatcherService {
    fun dispatch(entry: Entry)
}
