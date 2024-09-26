package link.limecode.reddit.lite.presentation.viewmodel.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.domain.usecase.LoginUseCase
import link.limecode.reddit.lite.util.BaseViewModel
import link.limecode.reddit.lite.util.DomainException
import link.limecode.reddit.lite.util.runDomainCatching

class AndroidLoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    val tfUsername = MutableStateFlow("")
    val tfPassword = MutableStateFlow("")

    val errorUsername = MutableStateFlow<String?>(null)
    val errorPassword = MutableStateFlow<String?>(null)

    fun login() {
        viewModelScope.launch {
            val param = LoginUseCase.Param(
                username = tfUsername.value,
                password = tfPassword.value
            )

            try {
                val result = loginUseCase.runDomainCatching { invoke(param) }.getOrThrow()

                if (result is ApiResLogin.Fail) {
                    errorUsername.value = result.validation.username
                    errorPassword.value = result.validation.password
                    return@launch
                }

                if (result is ApiResLogin.Success) {

                }
            } catch (e: DomainException) {
                _errorMessages.value = e
            }
        }
    }
}