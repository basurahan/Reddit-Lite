package link.limecode.reddit.lite.backend.routes.authentication.resources

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.backend.BaseRoute

@Serializable
@Resource("register")
@Suppress("unused")
class Register(val parent: BaseRoute = BaseRoute())