package link.limecode.reddit.lite.routes.authentication.resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.BaseRoute

@Serializable
@Resource("login")
@Suppress("unused")
class Login(val parent: BaseRoute = BaseRoute())