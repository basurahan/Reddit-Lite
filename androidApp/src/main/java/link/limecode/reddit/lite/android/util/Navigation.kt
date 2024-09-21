package link.limecode.reddit.lite.android.util

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.forEach
import androidx.navigation.FloatingWindow
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import link.limecode.reddit.lite.android.R
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.ChatTabDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.CreateTabDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.HomeTabDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.NotificationTabDestination
import link.limecode.reddit.lite.android.navigation.destinations.home.tabs.ProfileTabDestination
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

fun BottomNavigationView.activate(navController: NavController) {
    this.setOnItemSelectedListener { item ->
        navController.switchTab(item.toRoute())
    }

    val weakReference = WeakReference(this)
    navController.addOnDestinationChangedListener(
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                val view = weakReference.get()
                if (view == null) {
                    navController.removeOnDestinationChangedListener(this)
                    return
                }
                if (destination is FloatingWindow) {
                    return
                }

                view.updateState(destination)
            }
        }
    )

    navController.currentDestination?.let { updateState(it) }
}

private fun BottomNavigationView.updateState(destination: NavDestination) {
    when {
        destination.hierarchy.any { it.route == HomeTabDestination::class.qualifiedName } -> {
            menu.forEach { item ->
                if (item.itemId == R.id.home_tab) {
                    item.isChecked = true
                }
            }
        }

        destination.hierarchy.any { it.route == ChatTabDestination::class.qualifiedName } -> {
            menu.forEach { item ->
                if (item.itemId == R.id.chat_tab) {
                    item.isChecked = true
                }
            }
        }

        destination.hierarchy.any { it.route == CreateTabDestination::class.qualifiedName } -> {
            menu.forEach { item ->
                if (item.itemId == R.id.create_tab) {
                    item.isChecked = true
                }
            }
        }

        destination.hierarchy.any { it.route == NotificationTabDestination::class.qualifiedName } -> {
            menu.forEach { item ->
                if (item.itemId == R.id.notification_tab) {
                    item.isChecked = true
                }
            }
        }

        destination.hierarchy.any { it.route == ProfileTabDestination::class.qualifiedName } -> {
            menu.forEach { item ->
                if (item.itemId == R.id.profile_tab) {
                    item.isChecked = true
                }
            }
        }
    }
}

fun NavController.switchTab(route: KClass<out Any>): Boolean {
    if (route.qualifiedName == null) return false

    val navController = this
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setRestoreState(true)

    builder.setPopUpTo(
        navController.graph.findStartDestination().id,
        inclusive = false,
        saveState = true
    )

    val options = builder.build()
    return try {
        navController.navigate(
            route = route.qualifiedName!!,
            navOptions = options
        )
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}

private fun MenuItem.toRoute(): KClass<out Any> {
    return when (itemId) {
        R.id.home_tab -> HomeTabDestination::class
        R.id.chat_tab -> ChatTabDestination::class
        R.id.create_tab -> CreateTabDestination::class
        R.id.notification_tab -> NotificationTabDestination::class
        R.id.profile_tab -> ProfileTabDestination::class
        else -> error("Illegal State, tab not found")
    }
}