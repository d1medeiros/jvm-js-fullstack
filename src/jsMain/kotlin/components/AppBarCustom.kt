package components

import csstype.number
import csstype.px
import emotion.react.css
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.icons.material.Menu
import mui.material.*
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.ReactNode
import react.router.dom.Link
import react.router.useNavigate
import react.useState

private val scope = MainScope()

external interface AppBarCustomProps : Props {
    var typography: String
    var date: String
}

val AppBarCustom = FC<AppBarCustomProps> {
    val (isOpen, setIsOpen) = useState(false)
    val navigate = useNavigate()
    AppBar {
        position = AppBarPosition.static
        Container {
            maxWidth = "xl"
            Toolbar{
                disableGutters = true
                IconButton {
                    Menu()
                    css{
                        flexGrow = number(1.0)
                    }
                    onClick = {
                        scope.launch {
                            setIsOpen(!isOpen)
                        }
                    }

                }
                Box{
                    Typography {
                        noWrap = true
                        variant = TypographyVariant.h6
                        +it.typography

                    }
                }
            }
        }
    }
    Box {
        Drawer{
            open = isOpen
            onClose = { _: dynamic, _: String ->
                scope.launch {
                    setIsOpen(!isOpen)
                }
            }
            Box{
                List{
                    for (i in pages) {
                        ListItem{
                            ListItemButton{
                                Typography{
                                    +i.key
                                }
                                onClick = {
                                    navigate.invoke(i.value)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

val lixo = FC<AppBarCustomProps>{
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

val pages =  mapOf(
    "default" to "/default",
    "daily" to "/",
    "delayed" to "/delayed",
)