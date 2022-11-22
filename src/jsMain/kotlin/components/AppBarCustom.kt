package components

import Subject
import csstype.px
import emotion.react.css
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.material.*
import react.FC
import react.Props
import react.ReactNode
import react.router.dom.Link
import timesPlus

private val scope = MainScope()

external interface AppBarCustomProps : Props {
    var typography: String
    var date: String
}

val AppBarCustom = FC<AppBarCustomProps> {
    AppBar {
        position = AppBarPosition.static
        Toolbar {
            Typography {
                +"${it.typography} ${it.date}"
                css {
                    paddingRight = 10.px
                }
            }
            Divider {
                orientation = Orientation.vertical
            }
            Chip {
                label = ReactNode("+ day")
                onClick = {
                    scope.launch {
                        timesPlus(Subject.DAY)
                    }
                }
            }
            Divider {
                orientation = Orientation.vertical
            }
            Chip {
                label = ReactNode("+ week")
                onClick = {
                    scope.launch {
                        timesPlus(Subject.WEEK)
                    }
                }
            }
            Divider {
                orientation = Orientation.vertical
            }
            Chip {
                label = ReactNode("+ month")
                onClick = {
                    scope.launch {
                        timesPlus(Subject.MONTH)
                    }
                }
            }
            Divider {
                orientation = Orientation.vertical
            }
            Link {
                to = "/default"
                Chip {
                    label = ReactNode("default")
                }
            }
            Divider {
                orientation = Orientation.vertical
            }
            Link {
                to = "/"
                Chip {
                    label = ReactNode("daily")
                }
            }
        }
    }
}