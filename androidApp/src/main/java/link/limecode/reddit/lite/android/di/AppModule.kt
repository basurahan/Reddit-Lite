package link.limecode.reddit.lite.android.di

import link.limecode.reddit.lite.data.client.KtorClient
import link.limecode.reddit.lite.data.client.getKtorClient
import org.koin.dsl.module

val appModule = module {
    single<KtorClient> { getKtorClient() }
}