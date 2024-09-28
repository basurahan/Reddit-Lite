package link.limecode.reddit.lite.presentation.viewmodel.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.domain.repository.TokenRepository
import link.limecode.reddit.lite.domain.usecase.CommonLoginUseCase
import link.limecode.reddit.lite.util.AndroidActionLiveData
import link.limecode.reddit.lite.util.AndroidBaseViewModel
import link.limecode.reddit.lite.util.DomainException
import link.limecode.reddit.lite.util.runDomainCatching

class AndroidLoginViewModel(
    private val loginUseCase: CommonLoginUseCase,
    private val tokenRepository: TokenRepository
) : AndroidBaseViewModel() {

    // events
    val onSessionStarted = AndroidActionLiveData<Unit>()

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
                val result = loginUseCase.runDomainCatching { invoke(param) }.getOrThrow()

                when (result) {
                    is ApiResLogin.Fail -> {
                        errorUsername.value = result.validation.username
                        errorPassword.value = result.validation.password
                    }

                    is ApiResLogin.Success -> {
                        startSession(result)
                        onSessionStarted.value = Unit
                    }
                }
            } catch (e: DomainException) {
                _errorMessages.value = e
            } finally {
                loadingAction.value = false
            }
        }
    }

    private suspend fun startSession(user: ApiResLogin.Success) {
        tokenRepository.saveToken(user.token)
    }
}