package components

import PAGES
import csstype.FontSize
import csstype.FontWeight
import csstype.number
import csstype.px
import emotion.react.css
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.icons.material.Menu
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
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
                        sx {
                            fontSize = FontSize.small
                            fontWeight = FontWeight.bold
                            marginLeft = 10.px
                        }
                        +it.typography
                    }
                    Typography {
                        sx {
                            fontSize = FontSize.xxSmall
                            marginLeft = 10.px
                        }
                        variant = TypographyVariant.overline
                        +it.date

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
                    for (i in PAGES) {
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
