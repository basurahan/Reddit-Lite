package link.limecode.reddit.lite.presentation.viewmodel.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.usecase.LoginUseCase
import link.limecode.reddit.lite.util.BaseViewModel
import link.limecode.reddit.lite.util.DomainException
import link.limecode.reddit.lite.util.runDomainCatching

class AndroidLoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    val username = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun login() {
        viewModelScope.launch {
            val param = LoginUseCase.Param(
                username = username.value,
                password = password.value
            )

            try {
                val result = loginUseCase.runDomainCatching { invoke(param) }.getOrThrow()
            } catch (e: DomainException) {
                _errorMessages.value = e
            }
        }
    }
}