package link.limecode.reddit.lite.util


import link.limecode.reddit.lite.data.di.android.androidDataModule
import link.limecode.reddit.lite.data.di.sharedDataModule
import link.limecode.reddit.lite.domain.di.sharedDomainModule
import link.limecode.reddit.lite.presentation.di.androidPresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initAndroidKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedDataModule, sharedDomainModule, androidDataModule, androidPresentationModule)
    }
}