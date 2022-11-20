import kotlinx.datetime.LocalDateTime
import java.util.UUID

class DailyEventTrigger(
    private val eventComponent: EventComponent
) : EventTrigger {
    override fun send(event: Event, dateTime: LocalDateTime): Event? {
        val frequency = event.frequency
        val afterOrEqual = dateTime.isAfterOrEqual(event.dataBase)
        val lastEvent = eventComponent.findAllDaily().firstOrNull {
            it.label == event.label
        }
        if (afterOrEqual && lastEvent == null) {
            val newEvent = event.copy(
                id = UUID.randomUUID().toString(),
                frequency = frequency?.copy(id = null),
                notebookId = dailyList
            )
            eventComponent.save(newEvent)
            eventComponent.updateDefault(event.id!!, frequency.getNextDateTime(event.dataBase))
        }
        return null
    }

}