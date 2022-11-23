import kotlinx.datetime.LocalDateTime
import java.util.*

class EventRepository {
    companion object {
        private val eventList = mutableListOf(
            Event(
                id = UUID.randomUUID().toString(),
                label = "Encher gasolina",
                resume = "Ir ao posto por gasolina no automovel",
                baseDate = LocalDateTime(2022, 11, 13, 0, 0),
                frequency = Frequency(1, 1, Subject.WEEK, 1),
                type = Type.CAR,
                notebookId = defaultList
            ),
            Event(
                id = UUID.randomUUID().toString(),
                label = "Lavar louça",
                resume = "Lavar louça gerada pelas refeições",
                baseDate = LocalDateTime(2022, 11, 13, 0, 0),
                frequency = Frequency(2, 1, Subject.DAY, 2),
                type = Type.HOME,
                notebookId = defaultList
            ),
            Event(
                id = UUID.randomUUID().toString(),
                label = "Check up",
                resume = "Verificação no medico com exames",
                baseDate = LocalDateTime(2022, 11, 13, 0, 0),
                frequency = Frequency(3, 1, Subject.YEAR, 3),
                type = Type.HEALTH,
                notebookId = defaultList
            ),
            Event(
                id = UUID.randomUUID().toString(),
                label = "Lavar carro",
                resume = "Ir no lava-jato para limpar o carro",
                baseDate = LocalDateTime(2022, 11, 13, 0, 0),
                frequency = Frequency(4, 1, Subject.MONTH, 4),
                type = Type.CAR,
                notebookId = defaultList
            ),
        )
    }

    fun findByIdAndNotebookId(id: String, notebookId: Long): Event? {
        return  eventList.firstOrNull {
            it.id == id && it.notebookId == notebookId
        }
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
        finishedU: Boolean? = null,
        closeDateU: LocalDateTime? = null,
    ) {
        eventList.firstOrNull {
            it.id == id && it.notebookId == notebookId
        }?.apply {
            if (dataBaseU != null) baseDate = dataBaseU
            if (activeU != null) active = activeU
            if (finishedU != null) {
                closeDateU ?: throw Exception("close date can't be null")
                finished = finishedU
                closeDate = closeDateU
            }
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

