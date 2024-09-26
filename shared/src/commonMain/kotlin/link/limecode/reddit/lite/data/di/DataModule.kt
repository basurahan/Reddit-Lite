package link.limecode.reddit.lite.data.di

import link.limecode.reddit.lite.data.client.KtorClient
import link.limecode.reddit.lite.data.client.getKtorClient
import link.limecode.reddit.lite.data.repository.AuthenticationRepositoryImpl
import link.limecode.reddit.lite.data.service.RedditLiteApiService
import link.limecode.reddit.lite.data.service.api.RedditLiteApi
import link.limecode.reddit.lite.domain.repository.AuthenticationRepository
import org.koin.dsl.module

val sharedDataModule = module {
    single<KtorClient> { getKtorClient() }
    single<RedditLiteApi> { RedditLiteApiService(get()) }
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}