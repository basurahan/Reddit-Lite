package link.limecode.reddit.lite.presentation.di

import link.limecode.reddit.lite.presentation.viewmodel.login.IOSLoginViewModelHelper
import org.koin.dsl.module

val presentationModule = module {
    factory<IOSLoginViewModelHelper> { IOSLoginViewModelHelper(get()) }
}