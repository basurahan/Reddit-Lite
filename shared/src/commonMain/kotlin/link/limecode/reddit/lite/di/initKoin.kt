package link.limecode.reddit.lite.di

import link.limecode.reddit.lite.data.di.dataModule
import link.limecode.reddit.lite.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(dataModule, domainModule)
    }
}