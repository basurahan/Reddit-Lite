package link.limecode.reddit.lite.android.navigation

import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import link.limecode.reddit.lite.android.navigation.params.home.ChatFragmentParam
import link.limecode.reddit.lite.android.navigation.params.home.CreateFragmentParam
import link.limecode.reddit.lite.android.navigation.params.home.HomeHomeFragmentParam
import link.limecode.reddit.lite.android.navigation.params.home.NotificationFragmentParam
import link.limecode.reddit.lite.android.navigation.params.home.ProfileFragmentParam
import link.limecode.reddit.lite.android.navigation.params.home.tabs.ChatTabParam
import link.limecode.reddit.lite.android.navigation.params.home.tabs.CreateTabParam
import link.limecode.reddit.lite.android.navigation.params.home.tabs.HomeTabParam
import link.limecode.reddit.lite.android.navigation.params.home.tabs.NotificationTabParam
import link.limecode.reddit.lite.android.navigation.params.home.tabs.ProfileTabParam
import link.limecode.reddit.lite.android.navigation.params.main.LoginFragmentParam
import link.limecode.reddit.lite.android.pages.home.chat.ChatFragment
import link.limecode.reddit.lite.android.pages.home.create.CreateFragment
import link.limecode.reddit.lite.android.pages.home.home.HomeHomeFragment
import link.limecode.reddit.lite.android.pages.home.notification.NotificationFragment
import link.limecode.reddit.lite.android.pages.home.profile.ProfileFragment
import link.limecode.reddit.lite.android.pages.login.LoginFragment

fun NavController.setupHomeGraph() {
    graph = createGraph(
        startDestination = HomeTabParam
    ) {
        navigation<HomeTabParam>(startDestination = HomeHomeFragmentParam) {
            fragment<HomeHomeFragment, HomeHomeFragmentParam>()
        }

        navigation<ChatTabParam>(startDestination = ChatFragmentParam) {
            fragment<ChatFragment, ChatFragmentParam>()
        }

        navigation<CreateTabParam>(startDestination = CreateFragmentParam) {
            fragment<CreateFragment, CreateFragmentParam>()
        }

        navigation<NotificationTabParam>(startDestination = NotificationFragmentParam) {
            fragment<NotificationFragment, NotificationFragmentParam>()
        }

        navigation<NotificationTabParam>(startDestination = NotificationFragmentParam) {
            fragment<NotificationFragment, NotificationFragmentParam>()
        }

        navigation<ProfileTabParam>(startDestination = ProfileFragmentParam) {
            fragment<ProfileFragment, ProfileFragmentParam>()
        }
    }
}