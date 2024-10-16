package link.limecode.reddit.lite.presentation.di

import link.limecode.reddit.lite.presentation.viewmodel.app.IOSSessionViewModelHelper
import link.limecode.reddit.lite.presentation.viewmodel.login.IOSLoginViewModelHelper
import org.koin.dsl.module

val iOSPresentationModule = module {
    factory<IOSLoginViewModelHelper> { IOSLoginViewModelHelper(get(), get()) }
    factory<IOSSessionViewModelHelper> { IOSSessionViewModelHelper(get(), get()) }
}