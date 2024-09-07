package link.limecode.reddit.lite.domain.dao

import link.limecode.reddit.lite.data.model.ApiPostAttachment
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPostAttachement

interface PostAttachementDao {
    
    suspend fun insert(attachment: ApiReqNewPostAttachement): ApiPostAttachment
}
