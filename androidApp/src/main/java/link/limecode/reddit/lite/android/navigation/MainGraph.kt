package link.limecode.reddit.lite.android.navigation

import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import link.limecode.reddit.lite.android.navigation.destinations.main.HomeFragmentDestination
import link.limecode.reddit.lite.android.navigation.destinations.main.LoginFragmentDestination
import link.limecode.reddit.lite.android.pages.home.HomeFragment
import link.limecode.reddit.lite.android.pages.login.LoginFragment

fun NavController.setupMainNavGraph() {
    graph = createGraph(
        startDestination = HomeFragmentDestination
    ) {
        fragment<HomeFragment, HomeFragmentDestination>()
        fragment<LoginFragment, LoginFragmentDestination>()
    }
}