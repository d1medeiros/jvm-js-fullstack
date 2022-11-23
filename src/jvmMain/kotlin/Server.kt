import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.minutes

var globalTime = LocalDateTime(2022, 11, 13, 0, 0)
fun main() {
    val eventRepository = EventRepository()
    val eventComponent = EventComponent(eventRepository)
    val mapEvent = arrayListOf(
        DailyEventTrigger(eventComponent),
        DelayedEventTrigger(eventComponent),
    ).asSequence()
    val timeControl = TimeControl(mapEvent)
    val eventManager = EventManager(eventComponent, timeControl)
    eventManager.run(globalTime)
    embeddedServer(Netty, 9090) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            allowMethod(HttpMethod.Get)
            allowMethod(HttpMethod.Post)
            allowMethod(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }
        routing {
            get("/") {
                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
            static("/") {
                resources("")
            }
            route("/times") {
                get {
                    call.respond(globalTime.toString())
                }
                put {
                    when {
                        call.request.queryParameters["plus"] == "DAY"
                        -> globalTime = globalTime.plusFrequency(
                            Frequency(
                                times = 1,
                                subject = Subject.DAY
                            )
                        )

                        call.request.queryParameters["plus"] == "WEEK"
                        -> globalTime = globalTime.plusFrequency(
                            Frequency(
                                times = 1,
                                subject = Subject.WEEK
                            )
                        )

                        call.request.queryParameters["plus"] == "MONTH"
                        -> globalTime = globalTime.plusFrequency(
                            Frequency(
                                times = 1,
                                subject = Subject.MONTH
                            )
                        )

                        call.request.queryParameters["minus"] == "DAY"
                        -> globalTime = globalTime.minusFrequency(
                            Frequency(
                                times = 1,
                                subject = Subject.DAY
                            )
                        )

                        call.request.queryParameters["minus"] == "WEEK"
                        -> globalTime = globalTime.minusFrequency(
                            Frequency(
                                times = 1,
                                subject = Subject.WEEK
                            )
                        )

                        call.request.queryParameters["minus"] == "MONTH"
                        -> globalTime = globalTime.minusFrequency(
                            Frequency(
                                times = 1,
                                subject = Subject.MONTH
                            )
                        )
                    }
                    eventManager.run(globalTime)
                    call.respond("ok")
                }
            }
            route("/events") {
                get {
                    val list = when {
                        call.request.queryParameters["type"] == "daily" -> eventComponent.findAllDaily()
                        call.request.queryParameters["type"] == "delayed" -> eventComponent.findAllDelayed()
                        else -> eventComponent.findAllDefault()
                    }
                    call.respond(list)
                }
                patch("/{id}") {
                    val id = call.parameters["id"] ?: ""
                    val event = call.receive(EventDTO::class)
                    when {
                        event.finished != null -> eventComponent.updateDaily(id, event.finished!!, globalTime)
                    }
                    call.respond("{}")
                }
                route("/batch") {
                    post {
                        call.respond(eventManager.run(Clock.System.now().toLocalDateTime(tz)))
                    }
                }
//                post {
//                    eventList += call.receive<Event>()
//                    call.respond(HttpStatusCode.OK)
//                }
//                delete("/{id}") {
//                    val id = call.parameters["id"]?.toLong() ?: error("Invalid delete request")
//                    eventList.removeIf { it.id == id }
//                    call.respond(HttpStatusCode.OK)
//                }
            }
        }
    }.start(wait = true)
}

class Scheduler(
    private val eventManager: EventManager
) {

    suspend fun init(scope: CoroutineScope) {
        val scheduledEventFlow = flow {
            while (true) {
                delay(5.minutes)
                emit(Clock.System.now().toLocalDateTime(tz))
            }
        }

        scheduledEventFlow.onEach {
            println("running scheduler ***")
            eventManager.run(it)
        }.launchIn(scope)
    }
}


const val defaultList = 1L
const val dailyList = 2L
const val delayedList = 3L
const val doneList = 4L