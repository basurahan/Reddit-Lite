package link.limecode.reddit.lite.domain.di

import link.limecode.reddit.lite.domain.usecase.AuthUsecase
import org.koin.dsl.module
import org.koin.ktor.plugin.RequestScope

val domainModule = module {
    scope<RequestScope> {
        scoped<AuthUsecase> { AuthUsecase(get()) }
    }
}