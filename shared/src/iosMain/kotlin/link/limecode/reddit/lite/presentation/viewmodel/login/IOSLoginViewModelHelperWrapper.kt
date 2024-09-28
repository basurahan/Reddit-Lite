package link.limecode.reddit.lite.presentation.viewmodel.login

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IOSLoginViewModelHelperWrapper : KoinComponent {
    private val viewModel: IOSLoginViewModelHelper by inject()
    fun getHelper(): IOSLoginViewModelHelper = viewModel
}