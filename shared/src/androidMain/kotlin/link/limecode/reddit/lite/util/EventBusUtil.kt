package link.limecode.reddit.lite.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

class EventBusUtil<T> {
    private val _liveData = MutableLiveData<T>()
    private val observers = ConcurrentHashMap<String, AtomicBoolean>()

    fun registerObserver(id: String, lifecycleOwner: LifecycleOwner, callBack: (T) -> Unit) {
        observers.computeIfAbsent(id) { AtomicBoolean(false) }

        _liveData.observe(lifecycleOwner) { value ->
            val isHandled = observers[id]?.get() ?: return@observe

            if (!isHandled) {
                callBack(value)
                handled(id)
            }
        }
    }

    fun emit(value: T) {
        observers.keys.forEach { key ->
            observers[key]?.set(false)
        }
        _liveData.value = value
    }

    private fun handled(key: String) {
        observers[key]?.set(true)
    }

    fun unregisterObserver(id: String) {
        observers.remove(id)
    }
}

