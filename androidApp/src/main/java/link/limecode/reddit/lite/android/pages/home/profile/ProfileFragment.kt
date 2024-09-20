package link.limecode.reddit.lite.android.pages.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import link.limecode.reddit.lite.android.R
import link.limecode.reddit.lite.android.databinding.FragmentProfileBinding
import link.limecode.reddit.lite.android.navigation.params.main.LoginFragmentParam
import link.limecode.vuebinder.FragmentViewBinding

class ProfileFragment : FragmentViewBinding<FragmentProfileBinding>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            login.setOnClickListener {
                requireActivity().findNavController(R.id.nav_host_fragment)
                    .navigate(LoginFragmentParam)
            }
        }
    }
}