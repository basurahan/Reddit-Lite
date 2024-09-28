package link.limecode.reddit.lite.backend.domain.di

import link.limecode.reddit.lite.backend.domain.usecase.AuthUseCase
import link.limecode.reddit.lite.backend.domain.usecase.PostUseCase
import org.koin.dsl.module
import org.koin.ktor.plugin.RequestScope

val domainModule = module {
    scope<RequestScope> {
        scoped<AuthUseCase> { AuthUseCase(get()) }
        scoped<link.limecode.reddit.lite.backend.domain.usecase.VoteUseCase> {
            link.limecode.reddit.lite.backend.domain.usecase.VoteUseCase(
                postVoteDao = get(),
                postDao = get()
            )
        }
        scoped<PostUseCase> { PostUseCase(postVoteDao = get(), postDao = get()) }
    }
}