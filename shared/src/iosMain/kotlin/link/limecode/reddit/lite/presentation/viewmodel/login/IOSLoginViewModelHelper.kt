package link.limecode.reddit.lite.presentation.viewmodel.login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.model.UiResLogin
import link.limecode.reddit.lite.domain.model.UiUser
import link.limecode.reddit.lite.domain.repository.SessionRepository
import link.limecode.reddit.lite.domain.usecase.CommonLoginUseCase
import link.limecode.reddit.lite.util.CommonDomainException
import link.limecode.reddit.lite.util.IOSDomainException
import link.limecode.reddit.lite.util.buildNSError
import link.limecode.reddit.lite.util.runCommonDomainCatching
import link.limecode.reddit.lite.util.runIOSCatching
import platform.Foundation.NSError

class IOSLoginViewModelHelper(
    private val loginUseCase: CommonLoginUseCase,
    private val sessionRepository: SessionRepository
) {
    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var loginJob: Job? = null

    fun login(param: CommonLoginUseCase.Param, callBack: (UiResLogin?, NSError?) -> Unit) {
        if (loginJob?.isActive == true) {
            return
        }

        loginJob = coroutineScope.launch {
            try {
                val result = loginUseCase
                    .runCommonDomainCatching {
                        runIOSCatching { invoke(param) }.getOrThrow()
                    }
                    .getOrThrow()

                callBack(result, null)
            } catch (e: CommonDomainException) {
                callBack(null, buildNSError(e.message))
            } catch (e: IOSDomainException) {
                callBack(null, buildNSError(e.message))
            }
        }
    }

    fun startSessionBy(user: UiUser, token: String, callBack: () -> Unit) {
        coroutineScope.launch {
            sessionRepository.setSessionBy(user = user, token = token)
            callBack()
        }
    }

    fun cancelCoroutines() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
}