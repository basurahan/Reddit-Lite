package link.limecode.reddit.lite.presentation.di

import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidAppEventsViewModel
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidSessionViewModel
import link.limecode.reddit.lite.presentation.viewmodel.login.AndroidLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidPresentationModule = module {
    viewModel<AndroidLoginViewModel> { AndroidLoginViewModel(get(), get()) }
    viewModel<AndroidAppEventsViewModel> { AndroidAppEventsViewModel() }
    viewModel<AndroidSessionViewModel> { AndroidSessionViewModel() }
}