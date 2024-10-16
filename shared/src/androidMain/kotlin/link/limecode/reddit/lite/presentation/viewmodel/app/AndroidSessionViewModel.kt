package link.limecode.reddit.lite.presentation.viewmodel.app

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.usecase.CommonLoadLastSessionUseCase
import link.limecode.reddit.lite.presentation.model.UiUserInfo
import link.limecode.reddit.lite.util.AndroidBaseViewModel

sealed interface SessionUIState {
    data object Initial: SessionUIState

    data class LoggedIn(
        val userInfo: UiUserInfo
    ): SessionUIState

    data object NotLoggedIn: SessionUIState
}

sealed interface SplashScreenUIState {
    data object Initial: SplashScreenUIState
    data object Ready: SplashScreenUIState
}

private data class AndroidSessionViewModelState(
    val isReady: Boolean = false,
    val userInfo: UiUserInfo? = null
) {
    fun toSessionUIState(): SessionUIState {
        return if (isReady) {
            if (userInfo != null) {
                SessionUIState.LoggedIn(userInfo)
            } else {
                SessionUIState.NotLoggedIn
            }
        } else {
            SessionUIState.Initial
        }
    }

    fun toSplashScreenUIState(): SplashScreenUIState {
        return if (isReady) {
            SplashScreenUIState.Ready
        } else {
            SplashScreenUIState.Initial
        }
    }
}

class AndroidSessionViewModel(
    loadLastSessionUseCase: CommonLoadLastSessionUseCase
) : AndroidBaseViewModel() {

    private val viewModelState = MutableStateFlow(AndroidSessionViewModelState())
    val sessionUIState = viewModelState
        .map { it.toSessionUIState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toSessionUIState()
        )
    val splashScreenUIState = viewModelState
        .map { it.toSplashScreenUIState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toSplashScreenUIState()
        )

    init {
        viewModelScope.launch {
            loadLastSessionUseCase.invoke().collect { userInfo ->
                viewModelState.update {
                    it.copy(isReady = true, userInfo = userInfo)
                }
            }
        }
    }
}