package link.limecode.reddit.lite.android.util

import android.content.Context
import android.util.TypedValue

fun Context.colorTertiary(): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(com.google.android.material.R.attr.colorTertiary, typedValue, true)
    return typedValue.data
}

fun Context.colorOnTertiary(): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(com.google.android.material.R.attr.colorOnTertiary, typedValue, true)
    return typedValue.data
}
