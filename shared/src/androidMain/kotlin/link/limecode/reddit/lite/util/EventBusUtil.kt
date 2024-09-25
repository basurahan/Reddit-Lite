package link.limecode.reddit.lite.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.ConcurrentHashMap

class EventBusUtil<T> {
    private val _liveData = MutableLiveData<T>()
    private val observers = ConcurrentHashMap<String, Boolean>()

    fun registerObserver(id: String, lifecycleOwner: LifecycleOwner, callBack: (T) -> Unit) {
        synchronized(observers) {
            if (!observers.containsKey(id)) {
                observers[id] = true
            }
        }

        _liveData.observe(lifecycleOwner) { value ->
            synchronized(observers) {
                if (observers[id] == true) return@observe

                handled(id)
                callBack(value)
            }
        }
    }

    fun emit(value: T) {
        synchronized(observers) {
            observers.keys.forEach { key ->
                observers[key] = false
            }
        }
        _liveData.value = value
    }

    private fun handled(key: String) {
        synchronized(observers) {
            observers[key] = true
        }
    }
}
