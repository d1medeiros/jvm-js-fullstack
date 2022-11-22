package components

import mui.material.Box
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC

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