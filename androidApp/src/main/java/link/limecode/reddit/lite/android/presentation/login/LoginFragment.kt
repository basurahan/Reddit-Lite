package link.limecode.reddit.lite.android.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.android.databinding.FragmentLoginBinding
import link.limecode.reddit.lite.presentation.viewmodel.app.AppEventsViewModel
import link.limecode.reddit.lite.presentation.viewmodel.login.AndroidLoginViewModel
import link.limecode.vuebinder.FragmentViewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : FragmentViewBinding<FragmentLoginBinding>() {

    private val viewModel: AndroidLoginViewModel by viewModel()
    private val eventsViewModel: AppEventsViewModel by activityViewModel()

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupDataObservers()
    }

    private fun setupViews() {
        with(viewBinding) {
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            tfUsername.doOnTextChanged { text, _, _, _ ->
                viewModel.username.value = text.toString()
            }

            tfPassword.doOnTextChanged { text, _, _, _ ->
                viewModel.password.value = text.toString()
            }

            btnLogin.setOnClickListener {
                viewModel.login()
            }

            /*requireActivity().showSoftKeyboardFor(
                scroll = nestedScroll,
                layout = layoutUsername,
                input = tfUsername
            )*/
        }
    }

    private fun setupDataObservers() {
        with(viewBinding) {
            viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
                Snackbar.make(viewBinding.root, message.message, Snackbar.LENGTH_LONG).show()
            }

            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    launch {
                        viewModel.username.collect { text ->
                            if (tfUsername.text.toString() != text) {
                                tfUsername.setText(text)
                            }
                        }
                    }

                    launch {
                        viewModel.password.collect { text ->
                            if (tfPassword.text.toString() != text) {
                                tfPassword.setText(text)
                            }
                        }
                    }
                }
            }
        }
    }
}