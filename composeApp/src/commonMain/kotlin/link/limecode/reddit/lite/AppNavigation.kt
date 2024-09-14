package link.limecode.reddit.lite

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import link.limecode.reddit.lite.ui.pages.home.tabs.chat.ChatTabScreen
import link.limecode.reddit.lite.ui.pages.home.tabs.create.CreatePostTabScreen
import link.limecode.reddit.lite.ui.pages.home.tabs.home.HomeTabScreen
import link.limecode.reddit.lite.ui.pages.home.tabs.notification.NotificationTabScreen
import link.limecode.reddit.lite.ui.pages.home.tabs.profile.ProfileTabScreen
import link.limecode.reddit.lite.ui.pages.login.LoginScreen

internal sealed class Screen(val route: String) {
    
    data object Home: Screen(route = "home")
    data object Login: Screen(route = "login")
}

enum class HomeTabs(val route: String) {
    HOME("home"),
    CHAT("chats"),
    CREATE("create"),
    NOTIFICATION("notifications"),
    PROFILE("profile")
}

private sealed class RouteBuilder(
    private val route: String
) {
    fun createRoute(root: Screen) = "${root.route}/$route"
    
    data object HomeTab: RouteBuilder(HomeTabs.HOME.route)
    data object ChatsTab: RouteBuilder(HomeTabs.CHAT.route)
    data object CreatePostTab: RouteBuilder(HomeTabs.CREATE.route)
    data object NotificationTab: RouteBuilder(HomeTabs.NOTIFICATION.route)
    data object ProfileTab: RouteBuilder(HomeTabs.PROFILE.route)
    
    data object Login: RouteBuilder("login")
}

@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        
        navigation(
            route = Screen.Home.route,
            startDestination = RouteBuilder.HomeTab.createRoute(Screen.Home)
        ) {
            addHomeStack()
            addChatsStack()
            addCreatePostStack()
            addNotificationStack()
            addProfileStack(
                navToLogin = { navController.navigate(Screen.Login.route) }
            )
        }
        
        navigation(
            route = Screen.Login.route,
            startDestination = RouteBuilder.Login.createRoute(Screen.Login)
        ) {
            composable(RouteBuilder.Login.createRoute(Screen.Login)) {
                LoginScreen(
                    onNavBack = { navController.navigateUp() }
                )
            }
        }
    }
}

private fun NavGraphBuilder.addHomeStack() {
    composable(RouteBuilder.HomeTab.createRoute(Screen.Home)) {
        HomeTabScreen()
    }
}

private fun NavGraphBuilder.addChatsStack() {
    composable(RouteBuilder.ChatsTab.createRoute(Screen.Home)) {
        ChatTabScreen()
    }
}

private fun NavGraphBuilder.addCreatePostStack() {
    composable(RouteBuilder.CreatePostTab.createRoute(Screen.Home)) {
        CreatePostTabScreen()
    }
}

private fun NavGraphBuilder.addNotificationStack() {
    composable(RouteBuilder.NotificationTab.createRoute(Screen.Home)) {
        NotificationTabScreen()
    }
}

private fun NavGraphBuilder.addProfileStack(
    navToLogin: () -> Unit
) {
    composable(RouteBuilder.ProfileTab.createRoute(Screen.Home)) {
        ProfileTabScreen(
            navToLogin = { navToLogin() }
        )
    }
}

@Composable
internal fun NavController.currentTabAsState(): State<HomeTabs?> {
    val selectedItem = remember { mutableStateOf<HomeTabs?>(HomeTabs.HOME) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == HomeTabs.HOME.asRoute() } -> {
                    selectedItem.value = HomeTabs.HOME
                }
                destination.hierarchy.any { it.route == HomeTabs.CHAT.asRoute() } -> {
                    selectedItem.value = HomeTabs.CHAT
                }
                destination.hierarchy.any { it.route == HomeTabs.CREATE.asRoute() } -> {
                    selectedItem.value = HomeTabs.CREATE
                }
                destination.hierarchy.any { it.route == HomeTabs.NOTIFICATION.asRoute() } -> {
                    selectedItem.value = HomeTabs.NOTIFICATION
                }
                destination.hierarchy.any { it.route == HomeTabs.PROFILE.asRoute() } -> {
                    selectedItem.value = HomeTabs.PROFILE
                }
                else  -> {
                    selectedItem.value = null
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}

internal fun HomeTabs.asRoute(): String {
    return when (this) {
        HomeTabs.HOME -> RouteBuilder.HomeTab.createRoute(Screen.Home)
        HomeTabs.CHAT -> RouteBuilder.ChatsTab.createRoute(Screen.Home)
        HomeTabs.CREATE -> RouteBuilder.CreatePostTab.createRoute(Screen.Home)
        HomeTabs.NOTIFICATION -> RouteBuilder.NotificationTab.createRoute(Screen.Home)
        HomeTabs.PROFILE -> RouteBuilder.ProfileTab.createRoute(Screen.Home)
    }
}