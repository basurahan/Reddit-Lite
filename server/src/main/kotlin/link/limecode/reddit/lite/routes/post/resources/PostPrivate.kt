package link.limecode.reddit.lite.routes.post.resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.BaseRoute

@Serializable
@Resource("/post")
@Suppress("unused")
class PostPrivate(val parent: BaseRoute = BaseRoute()) {
    
    @Serializable
    @Resource("new")
    class New(val parent: PostPrivate = PostPrivate())

    @Serializable
    @Resource("attachment")
    class Attachment(val parent: PostPrivate = PostPrivate()) {

        @Serializable
        @Resource("new")
        class New(val parent: Attachment = Attachment())
    }

    @Serializable
    @Resource("vote")
    class Vote(val parent: PostPrivate = PostPrivate())
}