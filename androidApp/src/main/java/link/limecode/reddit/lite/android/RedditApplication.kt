package link.limecode.reddit.lite.android

import android.app.Application
import link.limecode.reddit.lite.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RedditApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@RedditApplication)
        }
    }
}