package link.limecode.reddit.lite.util

import link.limecode.reddit.lite.data.di.dataModule
import link.limecode.reddit.lite.domain.di.domainModule
import link.limecode.reddit.lite.presentation.di.presentationModule
import org.koin.core.context.startKoin

fun initIOSKoin() {
    startKoin {
        modules(dataModule, domainModule, presentationModule)
    }
}