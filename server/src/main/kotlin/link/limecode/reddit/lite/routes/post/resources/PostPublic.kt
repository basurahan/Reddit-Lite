package link.limecode.reddit.lite.routes.post.resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.BaseRoute

@Serializable
@Resource("/public/post")
@Suppress("unused")
class PostPublic(val parent: BaseRoute = BaseRoute())