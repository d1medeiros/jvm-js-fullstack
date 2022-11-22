package components

import Event
import getEventDailyList
import getTimes
import isLimit
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import moveToDone
import mui.material.*
import mui.system.Container
import react.*

private val scope = MainScope()


external interface BaseContainerProps : Props {
    var title: String
}

val BaseContainer = FC<BaseContainerProps> {
    var eventList by useState(emptyList<Event>())
    var (cbIndex, setCbIndex) = useState("")
    var (detailIndexMO, setDetailIndexMO) = useState("")
    var dateTime by useState(null as LocalDateTime?)
    val title = it.title

    useEffectOnce {
        scope.launch {
            eventList = getEventDailyList()
            dateTime = LocalDateTime.parse(getTimes())
        }
    }

    document.bgColor = "gray"
    Container {
        maxWidth = "sm"
        fixed = true
        Card {
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
                                disabled = title !== "diario"
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
                                        ?.let { item.dataBase.isLimit(it) } ?: false
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}