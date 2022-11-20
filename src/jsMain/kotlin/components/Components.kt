package components

import csstype.Color
import csstype.px
import emotion.react.css
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.material.*
import mui.material.styles.TypographyVariant
import popper.core.modifiers.Padding
import react.*

external interface AppBarCustomProps : Props {
    var typography: String
}

external interface SimpleLabelProps : Props {
    var label: String
    var hidden: Boolean?
}

private val scope = MainScope()

val AppBarCustom = FC<AppBarCustomProps> {
    AppBar {
        position = AppBarPosition.static
        Toolbar {
            IconButton {
                mui.icons.material.Menu()
            }
            Typography{
                +it.typography
                css {
                    paddingRight = 10.px
                }
            }
            Divider {
                orientation = Orientation.vertical
            }
            react.router.dom.Link {
                to = "/"
                Chip {
                    label = ReactNode("daily")
                }
            }
            Divider {
                orientation = Orientation.vertical
            }
            react.router.dom.Link {
                to = "/default"
                Chip {
                    label = ReactNode("default")
                }
            }
            Divider {
                orientation = Orientation.vertical
            }
            react.router.dom.Link {
                to = "/delayed"
                Chip {
                    label = ReactNode("delayed")
                }
            }
        }
    }
}

val SecondaryListItemText = FC<SimpleLabelProps> {
    Box {
        Typography {
            +"detalhe resumido"
        }
        Typography {
            variant = TypographyVariant.overline
            +it.label
        }
        Typography {
            hidden = !(it.hidden ?: false)
            +"ver detalhe"
        }
    }
}
