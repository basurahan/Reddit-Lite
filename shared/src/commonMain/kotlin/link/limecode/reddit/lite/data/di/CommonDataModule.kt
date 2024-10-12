package link.limecode.reddit.lite.data.di

import link.limecode.reddit.lite.data.client.KtorClient
import link.limecode.reddit.lite.data.client.getKtorClient
import link.limecode.reddit.lite.data.repository.CommonAuthenticationRepositoryImpl
import link.limecode.reddit.lite.data.repository.CommonSessionRepositoryImpl
import link.limecode.reddit.lite.data.service.RedditLiteApiService
import link.limecode.reddit.lite.data.service.api.RedditLiteApi
import link.limecode.reddit.lite.domain.repository.AuthenticationRepository
import link.limecode.reddit.lite.domain.repository.SessionRepository
import org.koin.dsl.module

val commonDataModule = module {
    single<KtorClient> { getKtorClient() }
    single<RedditLiteApi> { RedditLiteApiService(get()) }
    single<AuthenticationRepository> { CommonAuthenticationRepositoryImpl(get()) }
    single<SessionRepository> { CommonSessionRepositoryImpl(get()) }
}