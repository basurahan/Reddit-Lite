package link.limecode.reddit.lite.presentation.viewmodel.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import link.limecode.reddit.lite.util.EventBusUtil


class AndroidAppViewModel : ViewModel() {

    val errorEventBus = EventBusUtil<String>()
}