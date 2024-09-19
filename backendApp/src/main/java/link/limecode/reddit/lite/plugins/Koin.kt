package link.limecode.reddit.lite.plugins

import io.ktor.server.application.*
import link.limecode.reddit.lite.data.di.dataModule
import link.limecode.reddit.lite.domain.di.domainModule
import org.koin.ktor.plugin.koin

fun Application.configureDependencyInjection() {
    koin {
        modules(dataModule, domainModule)
    }
}