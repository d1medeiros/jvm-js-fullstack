import kotlinx.datetime.*

val tz = TimeZone.UTC
fun LocalDateTime.plusFrequency(frequency: Frequency): LocalDateTime {
    val t = frequency.times.toLong()
    return when (frequency.subject) {
        Subject.DAY -> plus(this, t, DateTimeUnit.DAY)
        Subject.WEEK -> plus(this, t, DateTimeUnit.WEEK)
        Subject.MONTH -> plus(this, t, DateTimeUnit.MONTH)
        Subject.YEAR -> plus(this, t, DateTimeUnit.YEAR)
        else -> this
    }
}

fun plus(ldt: LocalDateTime, time: Long, dtu: DateTimeUnit): LocalDateTime {
   return ldt.toInstant(tz).plus(time, dtu, tz).toLocalDateTime(tz)
}

fun minus(ldt: LocalDateTime, time: Long, dtu: DateTimeUnit): LocalDateTime {
   return ldt.toInstant(tz).minus(time, dtu, tz).toLocalDateTime(tz)
}

fun LocalDateTime.minusFrequency(frequency: Frequency): LocalDateTime {
    val t = frequency.times.toLong()
    return when (frequency.subject) {
        Subject.DAY -> minus(this, t, DateTimeUnit.DAY)
        Subject.WEEK -> minus(this, t, DateTimeUnit.WEEK)
        Subject.MONTH -> minus(this, t, DateTimeUnit.MONTH)
        Subject.YEAR -> minus(this, t, DateTimeUnit.YEAR)
        else -> this
    }
}

fun LocalDateTime.isAfterOrEqual(date: LocalDateTime): Boolean {
    return this.toJavaLocalDateTime().isAfter(date.toJavaLocalDateTime())
        .or(this.toJavaLocalDateTime().isEqual(date.toJavaLocalDateTime()))
}

fun LocalDateTime.isLimit(date: LocalDateTime): Boolean {
    val plusWeeks = plus(this, 1L, DateTimeUnit.WEEK)
    return date.isAfterOrEqual(plusWeeks)
}

fun Frequency?.getNextDateTime(dataBase: LocalDateTime): LocalDateTime {
   return this?.let {
        dataBase.plusFrequency(it)
    } ?: throw Exception("frequency is null")
}


fun Byte.toBoolean(): Boolean {
    return when (this) {
        0.toByte() -> true
        else -> false
    }
}
fun Boolean.toByte(): Byte {
    return when (this) {
        true -> 0.toByte()
        else -> 1.toByte()
    }
}