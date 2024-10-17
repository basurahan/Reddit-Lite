package link.limecode.reddit.lite.android.util

import android.content.Context
import android.util.TypedValue

fun Context.colorPrimary(): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true)
    return typedValue.data
}

fun Context.colorOnPrimary(): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(com.google.android.material.R.attr.colorOnPrimary, typedValue, true)
    return typedValue.data
}
