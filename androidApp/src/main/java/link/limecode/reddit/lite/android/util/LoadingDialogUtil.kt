package link.limecode.reddit.lite.android.util

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import link.limecode.reddit.lite.android.R
import link.limecode.reddit.lite.android.databinding.DialogLoadingBinding

fun Context.buildLoadingDialog(message: String? = null): AlertDialog {
    val dialog = MaterialAlertDialogBuilder(this)
    val view = DialogLoadingBinding.inflate(LayoutInflater.from(this), null, false)
    view.message.text = message ?: getString(R.string.loading)
    dialog.setView(view.root)
    dialog.setCancelable(false)
    return dialog.create()
}