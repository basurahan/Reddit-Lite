package link.limecode.reddit.lite.android.pages.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import link.limecode.reddit.lite.android.databinding.FragmentLoginBinding
import link.limecode.reddit.lite.android.util.BaseKeyboardSafeCoordinatorLayout
import link.limecode.reddit.lite.android.util.showSoftKeyboardFor
import link.limecode.reddit.lite.presentation.viewmodel.login.AndroidLoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseKeyboardSafeCoordinatorLayout<FragmentLoginBinding>() {

    private val viewModel: AndroidLoginViewModel by viewModel()

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
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