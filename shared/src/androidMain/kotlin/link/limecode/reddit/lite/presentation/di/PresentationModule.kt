package link.limecode.reddit.lite.presentation.di

import link.limecode.reddit.lite.presentation.viewmodel.app.AppEventsViewModel
import link.limecode.reddit.lite.presentation.viewmodel.login.AndroidLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<AndroidLoginViewModel> { AndroidLoginViewModel(get()) }
    viewModel<AppEventsViewModel> { AppEventsViewModel() }
}