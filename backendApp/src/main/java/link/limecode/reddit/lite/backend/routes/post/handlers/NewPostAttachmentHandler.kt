package link.limecode.reddit.lite.backend.routes.post.handlers

import link.limecode.reddit.lite.backend.domain.dao.PostAttachmentDao
import link.limecode.reddit.lite.backend.exceptions.UnexpectedDataException
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPostAttachement
import link.limecode.reddit.lite.data.model.response.post.ApiResNewPostAttachment

suspend fun ApiReqNewPostAttachement?.handleNewPostAttachment(
    postAttachmentDao: PostAttachmentDao
): ApiResNewPostAttachment {
    if (this == null) throw UnexpectedDataException()

    val result = postAttachmentDao.insert(this)
    return ApiResNewPostAttachment(result)
}