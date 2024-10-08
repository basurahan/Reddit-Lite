package link.limecode.reddit.lite.android.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import link.limecode.reddit.lite.android.R
import link.limecode.reddit.lite.android.databinding.FragmentHomeBinding
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.HomeTabDestination
import link.limecode.reddit.lite.android.navigation.setupHomeGraph
import link.limecode.reddit.lite.android.util.activate
import link.limecode.reddit.lite.android.util.switchTab
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidAppEventsViewModel
import link.limecode.vuebinder.FragmentViewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : FragmentViewBinding<FragmentHomeBinding>() {

    private val appEventsViewModel: AndroidAppEventsViewModel by activityViewModel()
    private lateinit var tabNavController: NavController

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavGraph()
        setupEventObservers()
    }

    private fun setupNavGraph() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.tab_host_fragment) as NavHostFragment
        tabNavController = navHostFragment.navController

        tabNavController.setupHomeGraph()
        with(viewBinding) {
            bottomNavigation.activate(tabNavController)
        }
    }

    private fun setupEventObservers() {
        with(viewBinding) {
            HomeFragment::class.qualifiedName?.let {
                appEventsViewModel.onUserSessionStarted.registerObserver(
                    id = it,
                    lifecycleOwner = viewLifecycleOwner
                ) {
                    tabNavController.switchTab(HomeTabDestination::class)
                    bottomNavigation.postDelayed(
                        {
                            Snackbar.make(
                                parent,
                                getString(R.string.login_successful),
                                Snackbar.LENGTH_LONG
                            ).setAnchorView(bottomNavigation).show()
                        },
                        200
                    )
                }
            }
        }
    }
}