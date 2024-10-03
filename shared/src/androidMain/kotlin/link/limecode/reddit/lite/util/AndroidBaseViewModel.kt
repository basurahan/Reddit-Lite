package link.limecode.reddit.lite.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

open class AndroidBaseViewModel : ViewModel() {

    @Suppress("PropertyName")
    protected val _errorMessages = AndroidActionLiveData<CommonDomainException>()
    val errorMessage: LiveData<CommonDomainException> = _errorMessages
}