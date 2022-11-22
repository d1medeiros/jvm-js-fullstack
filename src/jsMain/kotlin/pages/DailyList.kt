package pages

import components.BaseContainer
import kotlinx.coroutines.MainScope
import react.FC
import react.Props

private val scope = MainScope()

val DailyList = FC<Props> {
    BaseContainer{
        title = "diario"
    }
}