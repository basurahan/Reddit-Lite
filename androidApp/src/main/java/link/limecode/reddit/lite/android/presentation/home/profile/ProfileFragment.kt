package link.limecode.reddit.lite.android.presentation.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.android.R
import link.limecode.reddit.lite.android.base.BaseFragment
import link.limecode.reddit.lite.android.databinding.FragmentProfileBinding
import link.limecode.reddit.lite.android.util.showConfirmationDialog
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidAppEventsViewModel
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidSessionViewModel
import link.limecode.reddit.lite.presentation.viewmodel.app.SessionUIState
import link.limecode.reddit.lite.presentation.viewmodel.profile.AndroidProfileViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val sessionViewModel: AndroidSessionViewModel by activityViewModel()
    private val appEventsViewModel: AndroidAppEventsViewModel by activityViewModel()
    private val profileViewModel: AndroidProfileViewModel by viewModel()

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupDataObservers()
    }

    private fun setupViews() {
        with(viewBinding) {
            toolbar.menu[0].setOnMenuItemClickListener {
                requireContext().showConfirmationDialog(
                    title = getString(R.string.please_confirm),
                    message = getString(R.string.do_you_really_want_to_logout),
                    positiveButton = getString(R.string.logout) to {
                        profileViewModel.logout()
                    },
                    negativeButton = getString(R.string.cancel) to {}
                )
                true
            }
        }
    }

    private fun setupDataObservers() {
        with(viewBinding) {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    launch {
                        sessionViewModel.sessionUIState.collect { state ->
                            if (state is SessionUIState.LoggedIn) {
                                username.text = state.userInfo.username
                            }
                        }
                    }

                    launch {
                        profileViewModel.blockInteraction.collect { block ->
                            if (block)
                                loadingDialog.show()
                            else
                                loadingDialog.dismiss()
                        }
                    }
                }
            }

            profileViewModel.onLogoutSuccess.observe(viewLifecycleOwner) {
                appEventsViewModel.onUserSessionDestroyed.emit(Unit)
            }
        }
    }
}