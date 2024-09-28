package link.limecode.reddit.lite.util

import link.limecode.reddit.lite.data.di.commonDataModule
import link.limecode.reddit.lite.domain.di.commonDomainModule
import link.limecode.reddit.lite.presentation.di.iOSPresentationModule
import org.koin.core.context.startKoin

fun initIOSKoin() {
    startKoin {
        modules(commonDataModule, commonDomainModule, iOSPresentationModule)
    }
}
