package link.limecode.reddit.lite.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import link.limecode.reddit.lite.domain.repository.SessionRepository
import link.limecode.reddit.lite.presentation.model.UiUserInfo

class CommonLoadLastSessionUseCase(private val repository: SessionRepository) {

    fun invoke(): Flow<UiUserInfo?> {
        return repository.getUserInfo().map {
            it?.let {
                UiUserInfo(
                    username = it.username,
                    email = it.email
                )
            }
        }
    }
}