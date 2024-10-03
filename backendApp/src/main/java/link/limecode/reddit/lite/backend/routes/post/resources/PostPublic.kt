package link.limecode.reddit.lite.backend.routes.post.resources

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.backend.BaseRoute

@Serializable
@Resource("/public/post")
@Suppress("unused")
class PostPublic(val parent: BaseRoute = BaseRoute())