package link.limecode.reddit.lite.util

import link.limecode.reddit.lite.data.di.dataModule
import link.limecode.reddit.lite.domain.di.domainModule
import link.limecode.reddit.lite.presentation.di.presentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initAndroidKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(dataModule, domainModule, presentationModule)
    }
}