package link.limecode.reddit.lite.presentation.di

import link.limecode.reddit.lite.presentation.viewmodel.login.IOSLoginViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory<IOSLoginViewModel> { IOSLoginViewModel(get()) }
}