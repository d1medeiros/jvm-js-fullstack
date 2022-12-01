package components

import mui.material.ListItemText
import react.FC
import react.create


val ListItemEvent = FC<EventDetailProps>{
    val event = it.event
    ListItemText {
        primary = ListItemEventDetail.create {
            this.event = event
            this.hidden = it.hidden
            this.dateRed = it.dateRed
        }
    }
}
