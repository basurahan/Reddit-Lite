package link.limecode.reddit.lite.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class AndroidActionLiveData<T> : MutableLiveData<T> {

    private val pending: AtomicBoolean

    constructor() : super() {
        pending = AtomicBoolean(false)
    }

    constructor(value: T) : super(value) {
        pending = AtomicBoolean(true)
    }

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }
}