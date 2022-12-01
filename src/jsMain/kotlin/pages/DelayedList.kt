package pages

import Event
import Page
import components.BaseContainer
import getEventDelayedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import react.FC
import react.Props

private val scope = MainScope()

val DelayedList = FC<Props> {
    BaseContainer{
        title = Page.DELAYED.translated
        getList = fun (s: CoroutineScope): Deferred<List<Event>> {
            return s.async {
                getEventDelayedList()
            }
        }
    }
}