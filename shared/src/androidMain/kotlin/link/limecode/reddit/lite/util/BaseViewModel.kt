package link.limecode.reddit.lite.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    @Suppress("PropertyName")
    protected val _errorMessages = ActionLiveData<DomainException>()
    val errorMessage: LiveData<DomainException> = _errorMessages
}