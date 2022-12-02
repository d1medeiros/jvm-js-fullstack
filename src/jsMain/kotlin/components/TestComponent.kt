package components

import Subject
import csstype.Color
import csstype.JustifyContent
import csstype.integer
import csstype.px
import emotion.react.css
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.material.Chip
import mui.material.Grid
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import timesPlus

private val scope = MainScope()

val TestComponent = FC<Props> {
    Grid {
        container = true
        css {
            zIndex = integer(111111)
            backgroundColor = Color("red")
            justifyContent = JustifyContent.center
        }
        Grid {
            item = true
            Typography {
                css {
                    color = Color("white")
                    padding = 5.px

                }
                variant = TypographyVariant.h4
                +"ambiente de teste"
            }
        }

        Grid {
            item = true
            spacing = responsive(2)
            Chip {
                label = ReactNode("+ day")
                css {
                    color = Color("white")
                }
                onClick = {
                    scope.launch {
                        timesPlus(Subject.DAY)
                    }
                }
            }
            Chip {
                label = ReactNode("+ week")
                css {
                    color = Color("white")
                }
                onClick = {
                    scope.launch {
                        timesPlus(Subject.WEEK)
                    }
                }
            }

            Chip {
                label = ReactNode("+ month")
                css {
                    color = Color("white")
                }
                onClick = {
                    scope.launch {
                        timesPlus(Subject.MONTH)
                    }
                }
            }
        }
    }
}

external interface HeaderComponentProps : Props {
    var debug: Boolean?
}

val HeaderComponent = FC<HeaderComponentProps> {
    return@FC when {
        it.debug ?: false -> TestComponent {}
        else -> Grid {
            container = true
            css {
                zIndex = integer(111111)
                backgroundColor = Color("#1976d2")
                justifyContent = JustifyContent.center
            }
            Grid {
                item = true
                Typography {
                    css {
                        color = Color("white")
                        padding = 5.px

                    }
                    variant = TypographyVariant.h6
                    +"TDAHELPER"
                }
            }
        }
    }

}