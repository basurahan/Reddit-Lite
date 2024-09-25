package link.limecode.reddit.lite.android.pages.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import link.limecode.reddit.lite.android.R
import link.limecode.reddit.lite.android.databinding.FragmentProfileBinding
import link.limecode.reddit.lite.android.navigation.destinations.main.LoginFragmentDestination
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidAppViewModel
import link.limecode.vuebinder.FragmentViewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ProfileFragment : FragmentViewBinding<FragmentProfileBinding>() {

    private val appViewModel: AndroidAppViewModel by activityViewModel()

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ProfileFragment::class.qualifiedName?.let {
            appViewModel.errorEventBus.registerObserver(
                id = it,
                lifecycleOwner = viewLifecycleOwner
            ) { message ->
                Snackbar.make(viewBinding.login, "Hello", Snackbar.LENGTH_LONG)
                    .setAnchorView(viewBinding.login).show()
            }
        }

        with(viewBinding) {
            login.setOnClickListener {
                //Snackbar.make(login, "Hello", Snackbar.LENGTH_LONG).setAnchorView(login).show()
                requireActivity().findNavController(R.id.nav_host_fragment)
                    .navigate(LoginFragmentDestination)
                //appViewModel.errorMessageFlow.emit("whats")
            }
        }
    }
}