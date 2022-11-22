package components

import mui.material.ListItemText
import react.FC
import react.ReactNode
import react.create


val ListItemEvent = FC<EventDetailProps>{
    val event = it.event
    ListItemText {
        primary = ReactNode(event.label)
        secondary = ListItemEventDetail.create(){
            this.event = event
            this.hidden = it.hidden
//            this.hidden = detailIndexMO == item.id
            this.dateRed = it.dateRed
//            this.dateRed = dateTime?.let{ event.dataBase.isLimit(it)} ?: false
        }
    }
}
