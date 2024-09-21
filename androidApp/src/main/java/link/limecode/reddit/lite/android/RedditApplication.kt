package link.limecode.reddit.lite.android

import android.app.Application
import link.limecode.reddit.lite.util.initAndroidKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RedditApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initAndroidKoin {
            androidLogger()
            androidContext(this@RedditApplication)
        }
    }
}