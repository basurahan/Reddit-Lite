package link.limecode.reddit.lite.presentation.viewmodel.app

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IOSSessionViewModelHelperWrapper : KoinComponent {
    private val viewModel: IOSSessionViewModelHelper by inject()
    fun getHelper(): IOSSessionViewModelHelper = viewModel
}