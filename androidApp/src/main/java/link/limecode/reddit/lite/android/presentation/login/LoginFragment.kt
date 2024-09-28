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
import link.limecode.reddit.lite.android.base.BaseFragment
import link.limecode.reddit.lite.android.util.showSoftKeyboardFor
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidAppEventsViewModel
import link.limecode.reddit.lite.presentation.viewmodel.login.AndroidLoginViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: AndroidLoginViewModel by viewModel()
    private val appEventsViewModel: AndroidAppEventsViewModel by activityViewModel()

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
                viewModel.tfUsername.value = text.toString()
                viewModel.errorUsername.value = null
            }

            tfPassword.doOnTextChanged { text, _, _, _ ->
                viewModel.tfPassword.value = text.toString()
                viewModel.errorPassword.value = null
            }

            btnLogin.setOnClickListener {
                viewModel.login()
            }

            requireActivity().showSoftKeyboardFor(
                parent = parent,
                input = tfUsername
            )
        }
    }

    private fun setupDataObservers() {
        with(viewBinding) {
            viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
                Snackbar.make(root, message.message, Snackbar.LENGTH_LONG).show()
            }

            viewModel.loadingAction.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading)
                    loadingDialog.show()
                else
                    loadingDialog.dismiss()
            }

            viewModel.onSessionStarted.observe(viewLifecycleOwner) {
                appEventsViewModel.onUserSessionStarted.emit(Unit)
                findNavController().navigateUp()
            }

            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    launch {
                        viewModel.tfUsername.collect { text ->
                            if (tfUsername.text.toString() != text) {
                                tfUsername.setText(text)
                            }
                        }
                    }

                    launch {
                        viewModel.tfPassword.collect { text ->
                            if (tfPassword.text.toString() != text) {
                                tfPassword.setText(text)
                            }
                        }
                    }

                    launch {
                        viewModel.errorUsername.collect { message ->
                            layoutUsername.error = message
                        }
                    }

                    launch {
                        viewModel.errorPassword.collect { message ->
                            layoutPassword.error = message
                        }
                    }
                }
            }
        }
    }
}