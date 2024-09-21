package link.limecode.reddit.lite.domain.di

import link.limecode.reddit.lite.domain.usecase.LoginUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { LoginUseCase(get())  }
}