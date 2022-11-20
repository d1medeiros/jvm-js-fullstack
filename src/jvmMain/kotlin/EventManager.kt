import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime

class EventManager(
    private val eventRepository: EventComponent,
    private val timeControl: TimeControl
) {


    // run every 1h
    fun run(now: LocalDateTime) = runBlocking {
        eventRepository.findAllDefault()
            .map {
                async {
                    timeControl.run(now, it)
                }
            }.awaitAll()
    }
}