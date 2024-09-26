package link.limecode.reddit.lite.util

import link.limecode.reddit.lite.data.di.sharedDataModule
import link.limecode.reddit.lite.domain.di.sharedDomainModule
import link.limecode.reddit.lite.presentation.di.iOSPresentationModule
import org.koin.core.context.startKoin

fun initIOSKoin() {
    startKoin {
        modules(sharedDataModule, sharedDomainModule, iOSPresentationModule)
    }
}