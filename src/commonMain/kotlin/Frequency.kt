import kotlinx.serialization.Serializable

@Serializable
data class Frequency(
    var id: Long? = null,
    var times: Int,
    var subject: Subject,
    var eventId: Long? = null,
)