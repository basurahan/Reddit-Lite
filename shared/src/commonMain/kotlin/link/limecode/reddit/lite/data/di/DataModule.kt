package link.limecode.reddit.lite.data.di

import link.limecode.reddit.lite.data.client.KtorClient
import link.limecode.reddit.lite.data.client.getKtorClient
import org.koin.dsl.module

val dataModule = module {
    single<KtorClient> { getKtorClient() }
}