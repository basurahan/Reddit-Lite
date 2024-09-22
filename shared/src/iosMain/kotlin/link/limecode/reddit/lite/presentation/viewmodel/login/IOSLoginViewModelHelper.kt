package link.limecode.reddit.lite.presentation.viewmodel.login

import io.ktor.client.call.body
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
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.domain.usecase.LoginUseCase
import link.limecode.reddit.lite.util.buildNSError
import platform.Foundation.NSError
import platform.Foundation.NSLog

class IOSLoginViewModelHelper(private val loginUseCase: LoginUseCase) {
    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var loginJob: Job? = null

    fun login(param: LoginUseCase.Param, callBack: (ApiResLogin?, NSError?) -> Unit): Job {
        loginJob = coroutineScope.launch {
            try {
                val result = loginUseCase.invoke(param)
                callBack(result, null)
            } catch (e: RedirectResponseException) {
                callBack(null, e.buildNSError(e.response.bodyAsText()))
                NSLog("spawned --> redirect")
            } catch (e: ClientRequestException) {
                val item = e.response.body<ApiResLogin>()
                callBack(item, null)
                NSLog("spawned --> client")
            } catch (e: ServerResponseException) {
                callBack(null, e.buildNSError(e.response.bodyAsText()))
                NSLog("spawned --> server")
            } catch (e: TimeoutCancellationException) {
                callBack(null, e.buildNSError(e.message))
                NSLog("spawned --> timeout")
            } catch (e: IOException) {
                callBack(null, e.buildNSError(e.message))
                NSLog("spawned --> io")
            }
        }

        return loginJob as Job
    }

    fun cancelCoroutines() {
        loginJob?.cancel()
    }
}