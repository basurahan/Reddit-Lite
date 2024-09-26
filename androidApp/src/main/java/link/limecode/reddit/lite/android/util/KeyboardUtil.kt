package link.limecode.reddit.lite.android.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
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