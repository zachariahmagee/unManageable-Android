package zm.wcc.unmanageable.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = charcoal,
    primaryVariant = tropaz,
    onPrimary = calypso,
    secondary = flame_red,
    onSecondary = ucla_gold,
    background = matte_black,
    onBackground = milk_white,
    onSurface = ash,
    surface = grey

)

private val LightColorPalette = lightColors(
    primary = charcoal,
    primaryVariant = tropaz,
    onPrimary = calypso,
    secondary = flame_red,
    onSecondary = ucla_gold,
    background = matte_black,
    onBackground = milk_white,
    onSurface = ash,
    surface = grey

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun UnManageableTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}