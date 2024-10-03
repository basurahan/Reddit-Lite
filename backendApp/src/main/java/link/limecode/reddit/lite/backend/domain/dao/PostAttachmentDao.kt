package link.limecode.reddit.lite.backend.domain.dao

import link.limecode.reddit.lite.data.model.ApiPostAttachment
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPostAttachement

interface PostAttachmentDao {

    suspend fun insert(attachment: ApiReqNewPostAttachement): ApiPostAttachment
}
