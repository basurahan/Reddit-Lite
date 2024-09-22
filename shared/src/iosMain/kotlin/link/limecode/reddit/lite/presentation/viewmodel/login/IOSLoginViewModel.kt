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
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.domain.usecase.LoginUseCase
import platform.Foundation.NSError
import platform.Foundation.NSLog

class IOSLoginViewModel(private val loginUseCase: LoginUseCase) {
    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var loginJob: Job? = null

    fun login(param: LoginUseCase.Param, callBack: (ApiResLogin?, NSError?) -> Unit): Job {
        return coroutineScope.launch {
            try {
                val result = loginUseCase.invoke(param)
                callBack(result, null)
                NSLog("success")
            } catch (e: RedirectResponseException) {
                callBack(null, NSError.errorWithDomain("MyErrorDomain", 1, null))
                NSLog(e.response.bodyAsText())
            } catch (e: ClientRequestException) {
                callBack(null, NSError.errorWithDomain("MyErrorDomain", 1, null))
                NSLog(e.response.bodyAsText())
            } catch (e: ServerResponseException) {
                callBack(null, NSError.errorWithDomain("MyErrorDomain", 1, null))
                NSLog(e.response.bodyAsText())
            } catch (e: TimeoutCancellationException) {
                callBack(null, NSError.errorWithDomain("MyErrorDomain", 1, null))
                NSLog("timeout")
            } catch (e: IOException) {
                callBack(null, NSError.errorWithDomain("MyErrorDomain", 1, null))
                NSLog("IOException")
            }
        }
    }
}