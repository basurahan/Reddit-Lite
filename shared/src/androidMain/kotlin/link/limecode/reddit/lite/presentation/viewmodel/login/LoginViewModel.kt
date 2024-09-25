package link.limecode.reddit.lite.presentation.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.usecase.LoginUseCase
import link.limecode.reddit.lite.util.DomainException
import link.limecode.reddit.lite.util.runDomainCatching

class AndroidLoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    //val errorMessageFlow = ActionFlow<String>(viewModelScope)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val param = LoginUseCase.Param(
                username = username,
                password = password
            )

            try {
                val result = loginUseCase.runDomainCatching { invoke(param) }.getOrThrow()

                //errorMessageFlow.emit("success")
            } catch (e: DomainException) {
                //errorMessageFlow.emit(e.message)
            }
        }
    }
}