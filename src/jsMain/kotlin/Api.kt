import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

import kotlinx.browser.window

val endpoint = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved

val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun getEventDefaultList(): List<Event> {
    return jsonClient.get("$endpoint/events").body()
}

suspend fun getEventDailyList(): List<Event> {
    return jsonClient.get("$endpoint/events?type=daily").body()
}

suspend fun getEventDelayedList(): List<Event> {
    return jsonClient.get("$endpoint/events?type=delayed").body()
}

suspend fun moveToDone(id: String) {
    jsonClient.patch("$endpoint/events/$id") {
        contentType(ContentType.Application.Json)
        setBody(EventDTO(finished =  true))
    }
}

suspend fun getTimes(): String {
    return jsonClient.get("$endpoint/times").body()
}

suspend fun timesPlus(subject: Subject) {
    jsonClient.put("$endpoint/times?plus=$subject")
}

suspend fun timesMinus(subject: Subject) {
    jsonClient.put("$endpoint/times?minus=$subject")
}