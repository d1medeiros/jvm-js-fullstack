package pages

import Event
import components.AppBarCustom
import components.SecondaryListItemText
import emotion.react.css
import getEventDefaultList
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.material.*
import mui.system.Container
import react.*

private val scope = MainScope()

val DefaultList = FC<Props> {
    var eventList by useState(emptyList<Event>())
    var (cbIndex, setCbIndex) = useState("")
    var (detailIndexMO, setDetailIndexMO) = useState("")

    useEffectOnce {
        scope.launch {
            eventList = getEventDefaultList()
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
                typography = "Lista default"
            }
            List {
                eventList.sortedByDescending(Event::id).forEach { item ->
                    ListItem {
                        key = item.id.toString()
                        alignItems = ListItemAlignItems.flexStart
                        ListItemButton {
                            css {
                            }
                            onClick = {
                                scope.launch {
                                    setCbIndex(item.id ?: "")
                                    println("click checkbox ${item.id}")
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