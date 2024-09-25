package link.limecode.reddit.lite.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class EventBusUtil<T> {
    private val _liveData = MutableLiveData<T>()
    private val observers = mutableMapOf<String, Boolean>()

    fun registerObserver(id: String, lifecycleOwner: LifecycleOwner, callBack: (T) -> Unit) {
        val oldObserver = observers[id]

        if (oldObserver == null) {
            handled(id)
        }

        _liveData.observe(lifecycleOwner) {
            val alreadyHandled = observers[id]

            if (alreadyHandled == null || alreadyHandled == true) return@observe

            handled(id)
            callBack(it)
        }
    }

    fun emit(value: T) {
        val keys = observers.keys.toList()
        keys.forEach { key ->
            observers[key] = false
        }
        _liveData.value = value
    }

    private fun handled(key: String) {
        observers[key] = true
    }
}