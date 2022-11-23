import kotlinx.datetime.LocalDateTime

class EventComponent(
    private val eventRepository: EventRepository
) {
    fun findAllDefault(): List<Event> {
        return eventRepository.getAll(defaultList)
    }

    fun findAllDaily(): List<Event> {
        return eventRepository.getAll(dailyList, false)
    }

    fun findAllDelayed(): List<Event> {
        return eventRepository.getAll(delayedList)
    }

    fun updateDefault(id: String, nextDateTime: LocalDateTime) {
        eventRepository.update(id = id, notebookId = defaultList, dataBaseU = nextDateTime)
    }

    fun updateDaily(id: String, finished: Boolean, closeDate: LocalDateTime) {
        eventRepository.update(id, dailyList, finishedU = finished, closeDateU = closeDate)
        eventRepository.findByIdAndNotebookId(id, defaultList)
            ?.takeIf { closeDate.isAfter(it.baseDate) }
            ?.run {
                eventRepository.update(id = this.id!!, notebookId = this.notebookId!!, dataBaseU = closeDate)
            }
    }

    fun save(event: Event) {
        eventRepository.save(event)
    }

    fun updateToDelayed(id: String) {
        eventRepository.moveFrom(id, dailyList, delayedList)
    }
}