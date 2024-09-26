package link.limecode.reddit.lite.presentation.viewmodel.app

import androidx.lifecycle.ViewModel
import link.limecode.reddit.lite.util.EventBusUtil

class AndroidAppEventsViewModel : ViewModel() {

    val onUserSessionStarted = EventBusUtil<Unit>()
}