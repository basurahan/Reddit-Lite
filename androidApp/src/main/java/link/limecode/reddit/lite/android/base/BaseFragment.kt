package link.limecode.reddit.lite.android.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import link.limecode.reddit.lite.android.util.buildLoadingDialog
import link.limecode.vuebinder.FragmentViewBinding

abstract class BaseFragment<T : ViewBinding> : FragmentViewBinding<T>() {
    protected lateinit var loadingDialog: AlertDialog

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = requireContext().buildLoadingDialog()
    }
}