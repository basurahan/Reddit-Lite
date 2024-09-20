package link.limecode.reddit.lite.android.navigation

import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import link.limecode.reddit.lite.android.navigation.params.main.HomeFragmentParam
import link.limecode.reddit.lite.android.navigation.params.main.LoginFragmentParam
import link.limecode.reddit.lite.android.pages.home.HomeFragment
import link.limecode.reddit.lite.android.pages.login.LoginFragment

fun NavController.setupMainNavGraph() {
    graph = createGraph(
        startDestination = HomeFragmentParam
    ) {
        fragment<HomeFragment, HomeFragmentParam>()
        fragment<LoginFragment, LoginFragmentParam>()
    }
}