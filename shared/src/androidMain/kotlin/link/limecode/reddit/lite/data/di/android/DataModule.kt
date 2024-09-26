package link.limecode.reddit.lite.data.di.android

import link.limecode.reddit.lite.data.repository.TokenRepositoryImpl
import link.limecode.reddit.lite.domain.repository.TokenRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val androidDataModule = module {
    single<TokenRepository> { TokenRepositoryImpl(androidApplication().applicationContext) }
}