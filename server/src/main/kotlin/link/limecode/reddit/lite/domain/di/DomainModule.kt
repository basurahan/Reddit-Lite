package link.limecode.reddit.lite.domain.di

import link.limecode.reddit.lite.domain.usecase.AuthUsecase
import link.limecode.reddit.lite.domain.usecase.VoteUseCase
import org.koin.dsl.module
import org.koin.ktor.plugin.RequestScope

val domainModule = module {
    scope<RequestScope> {
        scoped<AuthUsecase> { AuthUsecase(get()) }
        scoped<VoteUseCase> { VoteUseCase(postVoteDao = get(), postDao = get()) }
    }
}