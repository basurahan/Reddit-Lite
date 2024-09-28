package link.limecode.reddit.lite.data.di

import link.limecode.reddit.lite.data.repository.AndroidTokenRepositoryImpl
import link.limecode.reddit.lite.domain.repository.TokenRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val androidDataModule = module {
    single<TokenRepository> { AndroidTokenRepositoryImpl(androidApplication().applicationContext) }
}