package link.limecode.reddit.lite.android.pages.login

import android.view.LayoutInflater
import android.view.ViewGroup
import link.limecode.reddit.lite.android.databinding.FragmentLoginBinding
import link.limecode.vuebinder.FragmentViewBinding

class LoginFragment : FragmentViewBinding<FragmentLoginBinding>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }
}