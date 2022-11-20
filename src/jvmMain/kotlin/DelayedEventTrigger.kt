import kotlinx.datetime.LocalDateTime

class DelayedEventTrigger(
    private val eventComponent: EventComponent
) : EventTrigger {
    override fun send(event: Event, dateTime: LocalDateTime): Event? {
        val lastEvent = eventComponent.findAllDaily().firstOrNull {
            it.label == event.label && it.dataBase.isLimit(dateTime)
        }
        if (lastEvent != null) {
            eventComponent.updateToDelayed(event.id!!)
        }
        return null
    }
}

