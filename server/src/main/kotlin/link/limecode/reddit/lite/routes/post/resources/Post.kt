package link.limecode.reddit.lite.routes.post.resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.BaseRoute

@Serializable
@Resource("/post")
@Suppress("unused")
class Post(
    val parent: BaseRoute = BaseRoute(),
    val sort: String? = "new",
    val filter: String? = "all"
) {
    
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

    @Serializable
    @Resource("vote")
    class Vote(val parent: Post = Post())
}