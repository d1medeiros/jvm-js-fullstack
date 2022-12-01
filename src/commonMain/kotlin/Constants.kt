val ALLOWED_TO_CLOSE = arrayOf("diario")

const val DAILY = "daily"
const val DEFAULT = "default"
const val DELAYED = "delayed"
const val FINISHED = "finished"


const val defaultList = 1L
const val dailyList = 2L
const val delayedList = 3L
const val doneList = 4L

val PAGES = mapOf(
    DAILY to "/",
    DEFAULT to "/default",
    DELAYED to "/delayed",
    FINISHED to "/finished",
)

enum class Page(
    val label: String,
    val index: String,
    val translated: String,
) {
    DAILY("daily", "/", "diario"),
    DEFAULT("default", "/default", "padrão"),
    DELAYED("delayed", "/delayed", "atrasado"),
    FINISHED("finished", "/finished", "finalisado")
}