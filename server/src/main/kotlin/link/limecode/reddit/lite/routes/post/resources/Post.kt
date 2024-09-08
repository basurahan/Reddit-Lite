package link.limecode.reddit.lite.routes.post.resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.BaseRoute

@Serializable
@Resource("/post")
@Suppress("unused")
class Post(val parent: BaseRoute = BaseRoute()) {
    
    @Serializable
    @Resource("new")
    class New(val parent: Post = Post())

    @Serializable
    @Resource("attachment")
    class Attachment(val parent: Post = Post()) {

        @Serializable
        @Resource("new")
        class New(val parent: Attachment = Attachment())
    }
}