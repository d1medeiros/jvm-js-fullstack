import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class EventDTO(
    val id: String? = null,
    val label: String? = null,
    var baseDate: LocalDateTime? = null,
    var closeDate: LocalDateTime? = null,
    var frequency: Frequency? = null,
    var active: Boolean? = null,
    var finished: Boolean? = null,
    var notebookId: Long? = null,
)



