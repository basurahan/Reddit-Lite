package link.limecode.reddit.lite.backend.plugins

import io.ktor.server.application.Application
import link.limecode.reddit.lite.backend.data.di.dataModule
import link.limecode.reddit.lite.backend.domain.di.domainModule
import org.koin.ktor.plugin.koin

fun Application.configureDependencyInjection() {
    koin {
        modules(dataModule, domainModule)
    }
}