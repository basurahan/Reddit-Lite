package link.limecode.reddit.lite.android.pages.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import link.limecode.reddit.lite.android.databinding.FragmentLoginBinding
import link.limecode.reddit.lite.android.util.BaseKeyboardSafeCoordinatorLayout
import link.limecode.reddit.lite.android.util.showSoftKeyboardFor
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidAppViewModel
import link.limecode.reddit.lite.presentation.viewmodel.login.AndroidLoginViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseKeyboardSafeCoordinatorLayout<FragmentLoginBinding>() {

    private val viewModel: AndroidLoginViewModel by viewModel()
    private val appViewModel: AndroidAppViewModel by activityViewModel()

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoginFragment::class.qualifiedName?.let {
            appViewModel.errorEventBus.registerObserver(
                id = it,
                lifecycleOwner = viewLifecycleOwner
            ) { message ->
                Snackbar.make(viewBinding.root, message, Snackbar.LENGTH_LONG).show()
            }
        }

        with(viewBinding) {
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            buttonLogin.setOnClickListener {
                //appViewModel.message()
                appViewModel.errorEventBus.emit("Hello Man")
                //viewModel.login("dev.renz", "S@langa1998")
            }

            requireActivity().showSoftKeyboardFor(
                scroll = nestedScroll,
                layout = layoutUsername,
                input = username
            )
        }
    }
}