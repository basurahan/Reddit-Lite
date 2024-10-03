package link.limecode.reddit.lite.backend.routes.authentication.resources

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.backend.BaseRoute

@Serializable
@Resource("login")
@Suppress("unused")
class Login(val parent: BaseRoute = BaseRoute())