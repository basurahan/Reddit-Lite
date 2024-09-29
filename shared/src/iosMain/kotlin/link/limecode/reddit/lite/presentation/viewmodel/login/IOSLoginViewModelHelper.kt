package link.limecode.reddit.lite.presentation.viewmodel.login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.model.UiResLogin
import link.limecode.reddit.lite.domain.usecase.CommonLoginUseCase
import link.limecode.reddit.lite.util.CommonDomainException
import link.limecode.reddit.lite.util.buildNSError
import link.limecode.reddit.lite.util.runDomainCatching
import platform.Foundation.NSError

class IOSLoginViewModelHelper(private val loginUseCase: CommonLoginUseCase) {
    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var loginJob: Job? = null

    fun login(param: CommonLoginUseCase.Param, callBack: (UiResLogin?, NSError?) -> Unit): Job {
        loginJob = coroutineScope.launch {
            try {
                val result = loginUseCase
                    .runDomainCatching { invoke(param) }
                    .getOrThrow()

                callBack(result, null)
            } catch (e: CommonDomainException) {
                callBack(null, buildNSError(e.message))
            }
        }

        return loginJob as Job
    }

    fun cancelCoroutines() {
        loginJob?.cancel()
    }
}