package components

import ALLOWED_TO_CLOSE
import Event
import csstype.number
import getEventDailyList
import getTimes
import isLimit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import moveToDone
import mui.material.*
import mui.system.sx
import react.*

private val scope = MainScope()


external interface BaseContainerProps : Props {
    var title: String
    var getList: (CoroutineScope)-> Deferred<List<Event>>
}

val BaseContainer = FC<BaseContainerProps> {
    var eventList by useState(emptyList<Event>())
    var (cbIndex, setCbIndex) = useState("")
    var (detailIndexMO, setDetailIndexMO) = useState("")
    var dateTime by useState(null as LocalDateTime?)
    val title = it.title

    useEffectOnce {
        scope.launch {
            eventList =  it.getList(this).await()
            dateTime = LocalDateTime.parse(getTimes())
        }
    }

    Box {
        sx { flexGrow = number(1.0) }
        Card {
            variant = PaperVariant.outlined
            TestComponent {}
            AppBarCustom {
                typography = title
                date = dateTime?.date.toString()
            }
            List {
                when {
                    eventList.isEmpty() -> ListItem {
                        ListItemText {
                            +"vazio"
                        }
                    }

                    else -> eventList.forEach { item ->
                        ListItem {
                            key = item.id.toString()
                            alignItems = ListItemAlignItems.flexStart
                            ListItemButton {
                                disabled = ALLOWED_TO_CLOSE.any { n -> n == title }.not()
                                onClick = {
                                    scope.launch {
                                        val id = item.id ?: ""
                                        setCbIndex(id)
                                        if (id != "") {
                                            moveToDone(id)
                                            eventList = getEventDailyList()
                                        }
                                    }
                                }
                                ListItemIcon {
                                    Checkbox {
                                        checked = cbIndex == item.id
                                    }
                                }

                            }
                            ListItemButton {
                                onClick = {
                                    scope.launch {
                                        println("click 2 checkbox ${item.id}")
                                    }
                                }
                                onMouseOver = {
                                    scope.launch {
                                        setDetailIndexMO(item.id ?: "")
                                    }
                                }
                                onMouseLeave = {
                                    scope.launch {
                                        setDetailIndexMO("")
                                    }
                                }
                                ListItemEvent {
                                    this.event = item
                                    this.hidden = detailIndexMO == item.id
                                    this.dateRed = dateTime
                                        ?.takeIf { title === "diario" }
                                        ?.let { item.baseDate.isLimit(it) } ?: false
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}