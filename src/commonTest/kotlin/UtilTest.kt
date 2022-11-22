import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UtilTest {

    @Test
    internal fun isAfterOrEqual_1() {
        val higherDate = LocalDateTime(2022, 10, 10, 0,0)
        val lowerDate = LocalDateTime(2022, 10, 9, 0,0)
        val result = higherDate.isAfterOrEqual(lowerDate)
        assertTrue { result }
    }

    @Test
    internal fun isAfterOrEqual_2() {
        val higherDate = LocalDateTime(2022, 10, 10, 0,0)
        val lowerDate = LocalDateTime(2022, 10, 10, 0,0)
        val result = higherDate.isAfterOrEqual(lowerDate)
        assertTrue { result }
    }

    @Test
    internal fun isAfterOrEqual_3() {
        val higherDate = LocalDateTime(2022, 10, 10, 0,0)
        val lowerDate = LocalDateTime(2022, 10, 9, 0,0)
        val result = lowerDate.isAfterOrEqual(higherDate)
        assertFalse { result }
    }
}