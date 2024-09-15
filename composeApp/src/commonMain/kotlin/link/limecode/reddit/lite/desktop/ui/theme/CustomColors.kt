package link.limecode.reddit.lite.desktop.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val onSurfaceButtonBackground: Color = onSurfaceButtonBackgroundColor
)

val LocalCustomColors = staticCompositionLocalOf { CustomColors() }