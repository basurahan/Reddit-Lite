package link.limecode.reddit.lite.presentation.viewmodel.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.model.UiResLogin
import link.limecode.reddit.lite.domain.model.UiUser
import link.limecode.reddit.lite.domain.repository.SessionRepository
import link.limecode.reddit.lite.domain.usecase.CommonLoginUseCase
import link.limecode.reddit.lite.util.AndroidActionLiveData
import link.limecode.reddit.lite.util.AndroidBaseViewModel
import link.limecode.reddit.lite.util.AndroidDomainException
import link.limecode.reddit.lite.util.CommonDomainException
import link.limecode.reddit.lite.util.runAndroidDomainCatching
import link.limecode.reddit.lite.util.runCommonDomainCatching

class AndroidLoginViewModel(
    private val loginUseCase: CommonLoginUseCase,
    private val tokenRepository: SessionRepository
) : AndroidBaseViewModel() {

    // events
    val onSessionStarted = AndroidActionLiveData<UiUser>()

    // actions
    val loadingAction = AndroidActionLiveData<Boolean>()

    // ui states
    val tfUsername = MutableStateFlow("")
    val tfPassword = MutableStateFlow("")
    val errorUsername = MutableStateFlow<String?>(null)
    val errorPassword = MutableStateFlow<String?>(null)

    private var loginJob: Job? = null

    fun login() {
        if (loginJob?.isActive == true) {
            return
        }

        loginJob = viewModelScope.launch {
            loadingAction.value = true
            val param = CommonLoginUseCase.Param(
                username = tfUsername.value,
                password = tfPassword.value
            )

            try {
                val result = loginUseCase.runCommonDomainCatching {
                        runAndroidDomainCatching { invoke(param) }.getOrThrow()
                    }.getOrThrow()

                when (result) {
                    is UiResLogin.Fail -> {
                        errorUsername.value = result.validation.username
                        errorPassword.value = result.validation.password
                    }

                    is UiResLogin.Success -> {
                        startSession(result)
                    }
                }
            } catch (e: CommonDomainException) {
                _errorMessages.value = e
            } catch (e: AndroidDomainException) {
                _errorMessages.value = e
            } finally {
                loadingAction.value = false
            }
        }
    }

    private suspend fun startSession(info: UiResLogin.Success) {
        tokenRepository.setSessionBy(user = info.user, token = info.token)
        onSessionStarted.value = info.user
    }
}