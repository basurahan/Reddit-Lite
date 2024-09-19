package link.limecode.reddit.lite.routes.authentication.resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.BaseRoute


@Serializable
@Resource("register")
@Suppress("unused")
class Register(val parent: BaseRoute = BaseRoute())