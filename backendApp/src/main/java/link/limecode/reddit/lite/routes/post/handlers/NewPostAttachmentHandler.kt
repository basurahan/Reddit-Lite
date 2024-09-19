package link.limecode.reddit.lite.routes.post.handlers

import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPostAttachement
import link.limecode.reddit.lite.data.model.response.post.ApiResNewPostAttachment
import link.limecode.reddit.lite.domain.dao.PostAttachementDao
import link.limecode.reddit.lite.exceptions.UnexpectedDataException

suspend fun ApiReqNewPostAttachement?.handleNewPostAttachment(
    postAttachmentDao: PostAttachementDao
) : ApiResNewPostAttachment {
    if (this == null) throw UnexpectedDataException()
    
    val result = postAttachmentDao.insert(this)
    return ApiResNewPostAttachment(result)
}