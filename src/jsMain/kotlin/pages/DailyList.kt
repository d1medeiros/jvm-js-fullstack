package pages

import Event
import components.AppBarCustom
import components.SecondaryListItemText
import moveToDone
import emotion.react.css
import getEventDailyList
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.material.*
import mui.system.Container
import react.*

private val scope = MainScope()

val DailyList = FC<Props> {
    var eventList by useState(emptyList<Event>())
    var (cbIndex, setCbIndex) = useState("")
    var (detailIndexMO, setDetailIndexMO) = useState("")

    useEffectOnce {
        scope.launch {
            eventList = getEventDailyList()
            println("cbIndex: $cbIndex")
            println("detailIndexMO: $detailIndexMO")
        }
    }

    document.bgColor = "gray"
    Container {
        maxWidth = "sm"
        fixed = true
        Card {
            AppBarCustom {
                typography = "Lista diaria"
            }
            List {
                when {
                    eventList.isEmpty() -> ListItem{
                        ListItemText{
                            +"vazio"
                        }
                    }
                    else ->eventList.forEach { item ->
                        ListItem {
                            key = item.id.toString()
                            alignItems = ListItemAlignItems.flexStart
                            ListItemButton {
                                css {
                                }
                                onClick = {
                                    scope.launch {
                                        val id = item.id ?: ""
                                        setCbIndex(id)
                                        println("click checkbox ${id}")
                                        if (id != "") {
                                            println("deleting ${id}")
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
                                        println("mouseover 2 ${item.id}")
                                    }
                                }
                                onMouseLeave = {
                                    scope.launch {
                                        setDetailIndexMO("")
                                    }
                                }
                                ListItemText {
                                    primary = ReactNode(item.label)
                                    secondary = SecondaryListItemText.create(){
                                        this.label = item.dataBase.toString()
                                        this.hidden = detailIndexMO == item.id
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}