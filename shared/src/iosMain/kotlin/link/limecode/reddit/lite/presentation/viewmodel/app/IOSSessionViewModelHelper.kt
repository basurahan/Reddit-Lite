package link.limecode.reddit.lite.presentation.viewmodel.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.repository.SessionRepository
import link.limecode.reddit.lite.domain.usecase.CommonLoadLastSessionUseCase
import link.limecode.reddit.lite.presentation.model.UiUserInfo

class IOSSessionViewModelHelper (
    loadLastSessionUseCase: CommonLoadLastSessionUseCase,
    private val sessionRepository: SessionRepository
) {
    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val session = loadLastSessionUseCase.invoke()
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    fun collectSession(callBack: (UiUserInfo?) -> Unit) {
        coroutineScope.launch {
            session.collect {
                callBack(it)
            }
        }
    }

    fun logout(callBack: () -> Unit) {
        coroutineScope.launch {
            sessionRepository.logout()
            callBack()
        }
    }

    fun cancelCoroutines() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
}