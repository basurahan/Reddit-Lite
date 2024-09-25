package link.limecode.reddit.lite.android.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import link.limecode.reddit.lite.android.R
import link.limecode.reddit.lite.android.databinding.FragmentHomeBinding
import link.limecode.reddit.lite.android.navigation.setupHomeGraph
import link.limecode.reddit.lite.android.util.activate
import link.limecode.vuebinder.FragmentViewBinding

class HomeFragment : FragmentViewBinding<FragmentHomeBinding>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.tab_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.setupHomeGraph()
        with(viewBinding) {
            bottomNavigation.activate(navController)
        }
    }
}