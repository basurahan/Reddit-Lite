package link.limecode.reddit.lite.domain.di

import link.limecode.reddit.lite.domain.usecase.CommonLoadLastSessionUseCase
import link.limecode.reddit.lite.domain.usecase.CommonLoginUseCase
import org.koin.dsl.module

val commonDomainModule = module {
    factory { CommonLoginUseCase(get()) }
    factory { CommonLoadLastSessionUseCase(get()) }
}