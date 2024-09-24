package link.limecode.reddit.lite.android.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.NestedScrollView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun Activity.showSoftKeyboardFor(
    scroll: NestedScrollView,
    layout: TextInputLayout,
    input: TextInputEditText
) {
    scroll.post {
        scroll.smoothScrollTo(0, layout.bottom)
        scroll.post {
            input.requestFocus()
            input.post {
                val imm: InputMethodManager =
                    this.getSystemService(InputMethodManager::class.java)
                imm.showSoftInput(input, 0)
            }
        }
    }
}