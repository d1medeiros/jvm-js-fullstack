import kotlinx.datetime.LocalDateTime
import java.util.UUID

class EventRepository {
    companion object {
        private val eventList = mutableListOf(
            Event(
                id = UUID.randomUUID().toString(),
                label = "Encher gasolina",
                dataBase = LocalDateTime(2022, 11, 13, 0, 0),
                frequency = Frequency(1, 1, Subject.WEEK, 1),
                type = Type.CAR,
                notebookId = defaultList
            ),
            Event(
                id = UUID.randomUUID().toString(),
                label = "Lavar louça",
                dataBase = LocalDateTime(2022, 11, 13, 0, 0),
                frequency = Frequency(2, 1, Subject.DAY, 2),
                type = Type.HOME,
                notebookId = defaultList
            ),
            Event(
                id = UUID.randomUUID().toString(),
                label = "Check up",
                dataBase = LocalDateTime(2022, 11, 13, 0, 0),
                frequency = Frequency(3, 1, Subject.YEAR, 3),
                type = Type.HOME,
                notebookId = defaultList
            ),
            Event(
                id = UUID.randomUUID().toString(),
                label = "Lavar carro",
                dataBase = LocalDateTime(2022, 11, 13, 0, 0),
                frequency = Frequency(4, 1, Subject.MONTH, 4),
                type = Type.CAR,
                notebookId = defaultList
            ),
        )
    }

    fun getAll(
        notebookId: Long? = null,
        finished: Boolean? = false
    ): List<Event> {
        return when {
            notebookId != null -> eventList.filter {
                it.notebookId == notebookId
            }
            else -> return eventList
        }.filter {
            it.finished == finished
        }
    }

    fun save(event: Event) {
        eventList.add(event)
    }

    fun update(
        id: String,
        notebookId: Long,
        dataBaseU: LocalDateTime? = null,
        activeU: Boolean? = null,
        finishedU: Boolean? = null
    ) {
        eventList.firstOrNull {
            it.id == id && it.notebookId == notebookId
        }?.apply {
            if (dataBaseU != null) dataBase = dataBaseU
            if (activeU != null) active = activeU
            if (finishedU != null) finished = finishedU
        }
    }

    fun moveFrom(id: String, from: Long, to: Long) {
        eventList.firstOrNull {
            it.id == id && it.notebookId == from
        }?.apply {
            notebookId = to
        }
    }

}

