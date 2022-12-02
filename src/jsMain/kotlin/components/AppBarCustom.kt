package components

import Page
import csstype.number
import emotion.react.css
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.icons.material.Menu
import mui.material.*
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
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
            Toolbar {
                disableGutters = true
                IconButton {
                    Menu()
                    css {
                        flexGrow = number(1.0)
                    }
                    onClick = {
                        scope.launch {
                            setIsOpen(!isOpen)
                        }
                    }

                }
                Box {
                    css {
                        flexGrow = number(1.0)
                    }
                    Typography{
                        variant = TypographyVariant.overline
                        +it.typography.uppercase()
                    }
                }
                Box {
                    Typography{
                        variant = TypographyVariant.overline
                        +it.date
                    }
                }
            }
        }
    }
    Box {
        Drawer {
            open = isOpen
            onClose = { _: dynamic, _: String ->
                scope.launch {
                    setIsOpen(!isOpen)
                }
            }
            Box {
                List {
                    for (i in Page.values()) {
                        ListItem {
                            ListItemButton {
                                Typography {
                                    +i.translated
                                }
                                onClick = {
                                    navigate.invoke(i.index)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
