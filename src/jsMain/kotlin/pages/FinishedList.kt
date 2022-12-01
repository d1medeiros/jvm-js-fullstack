package pages

import Event
import Page
import components.BaseContainer
import getEventDailyList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import react.FC
import react.Props

private val scope = MainScope()

val FinishedList = FC<Props> {
    BaseContainer{
        title = Page.FINISHED.translated
        getList =  fun (s: CoroutineScope): Deferred<List<Event>> {
            return s.async {
                getEventDailyList(true)
            }
        }
    }
}