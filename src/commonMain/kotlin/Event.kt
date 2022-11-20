import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: String? = null,
    val label: String,
    @Contextual
    var dataBase: LocalDateTime,
    var frequency: Frequency? = null,
    var active: Boolean? = false,
    var finished: Boolean? = false,
    val type: Type,
    var notebookId: Long? = null,
)



