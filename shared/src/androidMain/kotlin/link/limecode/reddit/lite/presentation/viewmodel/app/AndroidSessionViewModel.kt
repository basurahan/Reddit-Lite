package link.limecode.reddit.lite.presentation.viewmodel.app

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import link.limecode.reddit.lite.domain.usecase.CommonLoadLastSessionUseCase
import link.limecode.reddit.lite.util.AndroidBaseViewModel

class AndroidSessionViewModel(
    loadLastSessionUseCase: CommonLoadLastSessionUseCase
) : AndroidBaseViewModel() {

    var isReady: Boolean = false

    private val session = loadLastSessionUseCase.invoke()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    init {
        viewModelScope.launch {
            session.collect {
                isReady = true
            }
        }
    }
}