package link.limecode.reddit.lite.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

open class AndroidBaseViewModel : ViewModel() {

    @Suppress("PropertyName")
    protected val _blockInteraction = MutableStateFlow(false)
    val blockInteraction: StateFlow<Boolean> = _blockInteraction.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    @Suppress("PropertyName")
    protected val _errorMessages = AndroidActionLiveData<CommonDomainParentException>()
    val errorMessage: LiveData<CommonDomainParentException> = _errorMessages
}