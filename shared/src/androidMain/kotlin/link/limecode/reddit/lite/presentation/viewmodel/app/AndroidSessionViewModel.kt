package link.limecode.reddit.lite.presentation.viewmodel.app

import kotlinx.coroutines.flow.MutableStateFlow
import link.limecode.reddit.lite.domain.model.UiUser
import link.limecode.reddit.lite.util.AndroidBaseViewModel

class AndroidSessionViewModel : AndroidBaseViewModel() {
    private val _session = MutableStateFlow<UiUser?>(null)

    fun startSessionBy(user: UiUser) {
        _session.value = user
    }

    fun endSession() {
        _session.value = null
    }
}