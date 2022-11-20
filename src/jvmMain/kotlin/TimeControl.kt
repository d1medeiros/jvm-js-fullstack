import kotlinx.datetime.LocalDateTime

class TimeControl(
    private val eventMap: Sequence<EventTrigger>
) {
    fun run(dateTime: LocalDateTime, event: Event) {
        for (eventTrigger in eventMap) {
            eventTrigger.send(event, dateTime)
        }
    }
}

interface EventTrigger {
    fun send(event: Event, dateTime: LocalDateTime): Event?
}

