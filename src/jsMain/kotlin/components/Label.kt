package components

import csstype.Display
import csstype.FontSize
import csstype.FontWeight
import csstype.em
import emotion.react.css
import mui.material.Box
import mui.material.Typography
import mui.system.sx
import react.FC
import react.Props


external interface LabelProps : Props {
    var label: String
    var value: String
}

val Label = FC<LabelProps>{
    Box {
        css {
            display = Display.flex
        }
        Typography {
            sx {
                fontSize = FontSize.xSmall
                fontWeight = FontWeight.normal
            }
            noWrap = true
            +"${it.label}:"
        }
        Typography {
            noWrap = true
            sx {
                fontSize = FontSize.xSmall
                fontWeight = FontWeight.lighter
                paddingLeft = 0.5.em
            }
            +it.value
        }
    }
}