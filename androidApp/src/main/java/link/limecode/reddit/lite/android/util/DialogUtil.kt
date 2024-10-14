package link.limecode.reddit.lite.android.util

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import link.limecode.reddit.lite.android.R
import link.limecode.reddit.lite.android.databinding.DialogLoadingBinding

fun Context.buildLoadingDialog(message: String? = null): AlertDialog {
    val dialog = MaterialAlertDialogBuilder(this)
    dialog.setCancelable(false)
    val view = DialogLoadingBinding.inflate(LayoutInflater.from(this), null, false)
    view.message.text = message ?: getString(R.string.loading)
    dialog.setView(view.root)
    return dialog.create()
}

fun Context.showConfirmationDialog(
    title: String,
    message: String,
    positiveButton: Pair<String, () -> Unit>,
    negativeButton: Pair<String, () -> Unit>
) {
    val dialog = MaterialAlertDialogBuilder(this)
    dialog.setCancelable(false)
    dialog.setTitle(title)
    dialog.setMessage(message)
    dialog.setPositiveButton(positiveButton.first) { d, _ ->
        positiveButton.second.invoke()
        d.dismiss()
    }
    dialog.setNegativeButton(negativeButton.first) { d, _ ->
        negativeButton.second.invoke()
        d.dismiss()
    }
    dialog.create().show()
}