package link.limecode.reddit.lite.presentation.viewmodel.login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.usecase.LoginUseCase

class IOSLoginViewModel(private val loginUseCase: LoginUseCase) {
    private var job: Job? = null
    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    var count = 0

    private val _data = MutableSharedFlow<String>()
    val data: SharedFlow<String>
        get() = _data

    fun updateData() {
        if (job?.isActive == true) {
            return
        }

        job = coroutineScope.launch {
            for (i in 1..100) {
                _data.emit("Hello ${count}")
                count++
                delay(2000)
            }
        }
    }

    fun cancelData() {
        job?.cancel()
        job = null
    }
}