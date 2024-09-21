package link.limecode.reddit.lite.presentation.viewmodel.login

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModelHelper : KoinComponent {
    private val viewModel: IOSLoginViewModel by inject()
    fun getViewModel(): IOSLoginViewModel = viewModel
}