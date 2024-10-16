package link.limecode.reddit.lite.presentation.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.repository.SessionRepository
import link.limecode.reddit.lite.util.AndroidActionLiveData
import link.limecode.reddit.lite.util.AndroidBaseViewModel

class AndroidProfileViewModel(
    private val sessionRepository: SessionRepository
) : AndroidBaseViewModel() {

    private var logoutJob: Job? = null

    private val _onLogoutSuccess = AndroidActionLiveData<Unit>()
    val onLogoutSuccess: LiveData<Unit> = _onLogoutSuccess

    fun logout() {
        if (logoutJob?.isActive == true) {
            return
        }

        logoutJob = viewModelScope.launch {
            _blockInteraction.update { true }
            sessionRepository.logout()
            _blockInteraction.update { false }
            _onLogoutSuccess.value = Unit
        }
    }
}