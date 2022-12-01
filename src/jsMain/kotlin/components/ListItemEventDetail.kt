package components

import Event
import csstype.FontSize
import csstype.FontWeight
import emotion.react.css
import mui.material.Box
import mui.material.Grid
import mui.material.GridDirection
import mui.system.responsive
import react.FC
import react.Props


external interface EventDetailProps : Props {
    var event: Event
    var hidden: Boolean?
    var dateRed: Boolean?
}


val ListItemEventDetail = FC<EventDetailProps> {
    val event = it.event
    val dateRed = it.dateRed ?: false
    Box {
        Grid {
            container = true
            spacing = responsive(1)
            direction = responsive(GridDirection.column)
            Grid {
                item = true
                css {
                    fontSize = FontSize.small
                }
                +event.label
            }
            Grid {
                item = true
                css {
                    fontSize = FontSize.small
                    fontWeight = FontWeight.lighter
                }
                +event.resume!!
            }
            Grid {
                item = true
                Label {
                    label = "data"
                    value = event.baseDate.toString()
                }
            }
            Grid {
                item = true
                hidden = event.closeDate == null
                Label {
                    label = "data conclusão"
                    value = event.closeDate?.let { c -> c.toString() } ?: ""
                }
            }
            Grid {
                item = true
                hidden = !(it.hidden ?: false)
                Label {
                    label = "frequencia"
                    value = "${event.frequency?.times}x ${event.frequency?.subject?.toString()?.lowercase()}"
                }
            }
            Grid {
                item = true
                hidden = !(it.hidden ?: false)
                Label {
                    label = "tipo"
                    value = event.type.toString().lowercase()
                }
            }
        }
    }
}

