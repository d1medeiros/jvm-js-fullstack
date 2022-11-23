import kotlinx.datetime.LocalDateTime

class DailyEventTrigger(
    private val eventComponent: EventComponent
) : EventTrigger {
    override fun send(event: Event, dateTime: LocalDateTime): Event? {
        val frequency = event.frequency
        val afterOrEqual = dateTime.isAfterOrEqual(event.baseDate)
        val lastEvent = eventComponent.findAllDaily().firstOrNull {
            it.label == event.label
        }
        if (afterOrEqual && lastEvent == null) {
            val newEvent = event.copy(
//                id = UUID.randomUUID().toString(),
                frequency = frequency?.copy(id = null),
                notebookId = dailyList
            )
            eventComponent.save(newEvent)
            eventComponent.updateDefault(event.id!!, frequency.getNextDateTime(event.baseDate))
        }
        return null
    }

}