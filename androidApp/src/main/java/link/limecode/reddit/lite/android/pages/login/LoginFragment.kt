package link.limecode.reddit.lite.android.pages.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import link.limecode.reddit.lite.android.databinding.FragmentLoginBinding
import link.limecode.reddit.lite.android.util.showSoftKeyboardFor
import link.limecode.reddit.lite.presentation.viewmodel.login.AndroidLoginViewModel
import link.limecode.vuebinder.FragmentViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : FragmentViewBinding<FragmentLoginBinding>() {

    private val viewModel: AndroidLoginViewModel by viewModel()

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ViewCompat.setOnApplyWindowInsetsListener(parent) { view, insets ->
                val coordinator = view as CoordinatorLayout
                val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom

                val param = coordinator.layoutParams as FrameLayout.LayoutParams
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

                insets
            }

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            requireActivity().showSoftKeyboardFor(
                scroll = nestedScroll,
                layout = layoutUsername,
                input = username
            )
        }
    }
}