package link.limecode.reddit.lite.android.pages.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import link.limecode.reddit.lite.android.databinding.FragmentLoginBinding
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
            button.setOnClickListener {
                viewModel.login(
                    username = "dev.renz",
                    password = "S@langa1998"
                )
            }
        }
    }
}