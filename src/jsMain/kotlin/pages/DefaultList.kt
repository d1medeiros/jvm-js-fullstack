package pages

import Event
import components.BaseContainer
import getEventDefaultList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import react.FC
import react.Props

private val scope = MainScope()

val DefaultList = FC<Props> {
    BaseContainer{
        title = "default"
        getList =  fun (s: CoroutineScope): Deferred<List<Event>> {
            return s.async {
                getEventDefaultList()
            }
        }
    }
}