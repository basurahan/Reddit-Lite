package link.limecode.reddit.lite.presentation.viewmodel.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.usecase.LoginUseCase

class AndroidLoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val param = LoginUseCase.Param(
                username = username,
                password = password
            )
            try {
                loginUseCase.invoke(param)
                Log.i("spawned", "success")
            } catch (e: RedirectResponseException) {
                Log.i("spawned", e.response.bodyAsText())
            } catch (e: ClientRequestException) {
                Log.i("spawned", e.response.bodyAsText())
            } catch (e: ServerResponseException) {
                Log.i("spawned", e.response.bodyAsText())
            } catch (e: TimeoutCancellationException) {
                Log.i("spawned", "timeout")
            } catch (e: IOException) {
                Log.i("spawned", "io exception")
            }
        }
    }
}