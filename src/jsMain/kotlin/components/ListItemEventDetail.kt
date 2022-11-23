package components

import Event
import csstype.Color
import csstype.FontWeight
import emotion.react.css
import mui.material.Box
import mui.material.Typography
import mui.material.styles.TypographyVariant
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
            +event.dataBase.toString()
        }
        Typography {
            hidden = !(it.hidden ?: false)
            variant = TypographyVariant.caption
            +"${event.frequency?.times}x ${event.frequency?.subject.toString()}"
        }
    }
}
