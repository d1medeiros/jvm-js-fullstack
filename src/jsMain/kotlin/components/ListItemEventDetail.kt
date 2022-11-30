package components

import Event
import csstype.Color
import csstype.FontWeight
import emotion.react.css
import mui.material.Box
import mui.material.Grid
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.responsive
import react.FC
import react.Props
import react.dom.html.ReactHTML.div


external interface EventDetailProps : Props {
    var event: Event
    var hidden: Boolean?
    var dateRed: Boolean?
}


val ListItemEventDetail = FC<EventDetailProps> {
    val event = it.event
    val dateRed = it.dateRed ?: false
    Box {
        Typography {
            +event.resume!!
        }
        Typography {
            variant = TypographyVariant.overline
            css {
                when {
                    dateRed -> {
                        color = Color("red")
                        fontWeight = FontWeight.bold
                    }
                }
            }
            +event.baseDate.toString()
        }
        div {
            hidden = !(it.hidden ?: false)

            Grid {
                container = true
                spacing = responsive(2)
                Grid {
                    item = true
                    css {
                        fontWeight = csstype.FontWeight.bold
                    }
                    +"frequencia:"
                }
                Grid {
                    item = true
                    +"${event.frequency?.times}x ${event.frequency?.subject?.toString()?.lowercase()}"
                }
            }
            Grid {
                container = true
                spacing = responsive(2)
                hidden = !(it.hidden ?: false)
                Grid {
                    item = true
                    css {
                        fontWeight = csstype.FontWeight.bold
                    }
                    +"tipo:"
                }
                Grid {
                    item = true
                    +event.type.toString().lowercase()
                }
            }
        }
    }
}
