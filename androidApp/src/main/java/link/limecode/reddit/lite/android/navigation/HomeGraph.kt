package link.limecode.reddit.lite.android.navigation

import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import link.limecode.reddit.lite.android.navigation.destinations.home.ChatFragmentDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.CreateFragmentDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.HomeHomeFragmentDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.NotificationFragmentDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.ProfileFragmentDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.ChatTabDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.CreateTabDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.HomeTabDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.NotificationTabDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.ProfileTabDestination
import link.limecode.reddit.lite.android.pages.home.chat.ChatFragment
import link.limecode.reddit.lite.android.pages.home.create.CreateFragment
import link.limecode.reddit.lite.android.pages.home.home.HomeHomeFragment
import link.limecode.reddit.lite.android.pages.home.notification.NotificationFragment
import link.limecode.reddit.lite.android.pages.home.profile.ProfileFragment

fun NavController.setupHomeGraph() {
    graph = createGraph(
        startDestination = HomeTabDestination
    ) {
        navigation<HomeTabDestination>(startDestination = HomeHomeFragmentDestination) {
            fragment<HomeHomeFragment, HomeHomeFragmentDestination>()
        }

        navigation<ChatTabDestination>(startDestination = ChatFragmentDestination) {
            fragment<ChatFragment, ChatFragmentDestination>()
        }

        navigation<CreateTabDestination>(startDestination = CreateFragmentDestination) {
            fragment<CreateFragment, CreateFragmentDestination>()
        }

        navigation<NotificationTabDestination>(startDestination = NotificationFragmentDestination) {
            fragment<NotificationFragment, NotificationFragmentDestination>()
        }

        navigation<NotificationTabDestination>(startDestination = NotificationFragmentDestination) {
            fragment<NotificationFragment, NotificationFragmentDestination>()
        }

        navigation<ProfileTabDestination>(startDestination = ProfileFragmentDestination) {
            fragment<ProfileFragment, ProfileFragmentDestination>()
        }
    }
}