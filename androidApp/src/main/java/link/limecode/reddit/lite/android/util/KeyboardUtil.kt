package link.limecode.reddit.lite.android.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

fun Activity.showSoftKeyboardFor(
    parent: CoordinatorLayout,
    input: TextInputEditText
) {
    parent.post {
        input.requestFocus()
        input.post {
            val imm: InputMethodManager =
                this.getSystemService(InputMethodManager::class.java)
            imm.showSoftInput(input, 0)
        }
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: android.view.View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}