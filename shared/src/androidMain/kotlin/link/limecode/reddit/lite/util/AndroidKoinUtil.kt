package link.limecode.reddit.lite.util

import link.limecode.reddit.lite.data.di.androidDataModule
import link.limecode.reddit.lite.data.di.commonDataModule
import link.limecode.reddit.lite.domain.di.commonDomainModule
import link.limecode.reddit.lite.presentation.di.androidPresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initAndroidKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(commonDataModule, commonDomainModule, androidDataModule, androidPresentationModule)
    }
}