package link.limecode.reddit.lite.android.util

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.AppBarLayout
import link.limecode.vuebinder.FragmentViewBinding

abstract class BaseKeyboardSafeCoordinatorLayout<T : ViewBinding> : FragmentViewBinding<T>() {

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        require(viewBinding.root is CoordinatorLayout) { "Illegal usage of ${this.javaClass.simpleName}, make sure your parent layout is coordinator layout" }
        val child = viewBinding.root.findAppbarLayout()
        requireNotNull(child) { "Illegal usage of ${this.javaClass.simpleName}, make sure to have an appbar child on your parent layout" }

        setupKeyboardSafety(child)
    }

    private fun setupKeyboardSafety(appbarLayout: AppBarLayout) {
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.root) { rootView, insets ->
            val coordinator =
                rootView as? CoordinatorLayout ?: return@setOnApplyWindowInsetsListener insets

            val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom

            val param = coordinator.layoutParams as? FrameLayout.LayoutParams
                ?: return@setOnApplyWindowInsetsListener insets

            if (param.bottomMargin != imeHeight) {
                param.setMargins(
                    param.leftMargin,
                    param.topMargin,
                    param.rightMargin,
                    imeHeight
                )
                coordinator.layoutParams = param
            }

            // somehow appbar layout is not in sync with the changes of the layout
            // so we gonna notify it manually
            coordinator.dispatchDependentViewsChanged(appbarLayout)

            return@setOnApplyWindowInsetsListener insets
        }
    }

    private fun View.findAppbarLayout(): AppBarLayout? {
        if (this !is ViewGroup) return null

        for (view in this.children) {
            if (view is AppBarLayout) {
                return view
            }
        }

        return null
    }

    override fun onDestroyView() {
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.root, null)
        super.onDestroyView()
    }
}