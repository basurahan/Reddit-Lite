package link.limecode.reddit.lite.presentation.viewmodel.app

import androidx.lifecycle.ViewModel
import link.limecode.reddit.lite.util.AndroidEventBusUtil

class AndroidAppEventsViewModel : ViewModel() {

    val onUserSessionStarted = AndroidEventBusUtil<String>()
    val onUserSessionDestroyed = AndroidEventBusUtil<Unit>()
}