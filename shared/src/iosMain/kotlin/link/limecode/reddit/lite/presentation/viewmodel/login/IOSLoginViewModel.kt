package link.limecode.reddit.lite.presentation.viewmodel.login

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.usecase.LoginUseCase
import platform.Foundation.NSLog

class IOSLoginViewModel(private val loginUseCase: LoginUseCase) {
    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var loginJob: Job? = null

    fun login(username: String, password: String) {
        if (loginJob?.isActive == true) {
            return
        }

        loginJob = coroutineScope.launch {
            val param = LoginUseCase.Param(
                username = username,
                password = password
            )

            try {
                loginUseCase.invoke(param)
                NSLog("success")
            } catch (e: RedirectResponseException) {
                NSLog(e.response.bodyAsText())
            } catch (e: ClientRequestException) {
                NSLog(e.response.bodyAsText())
            } catch (e: ServerResponseException) {
                NSLog(e.response.bodyAsText())
            } catch (e: TimeoutCancellationException) {
                NSLog("timeout")
            } catch (e: IOException) {
                NSLog("IOException")
            }
        }
    }
}