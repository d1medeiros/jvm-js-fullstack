import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: String? = null,
    val label: String,
    val resume: String? = null,
    var baseDate: LocalDateTime,
    var closeDate: LocalDateTime? = null,
    var frequency: Frequency? = null,
    var active: Boolean? = false,
    var finished: Boolean? = false,
    val type: Type,
    var notebookId: Long? = null,
)



