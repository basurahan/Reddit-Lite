package link.limecode.reddit.lite.backend.data.db.dao

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.backend.domain.dao.PostAttachmentDao
import link.limecode.reddit.lite.data.model.ApiPostAttachment
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPostAttachement

class PostAttachmentDaoImpl(private val table: PostgrestQueryBuilder) : PostAttachmentDao {

    override suspend fun insert(attachment: ApiReqNewPostAttachement): ApiPostAttachment {
        return table.insert(attachment) { select() }.decodeSingle<ApiPostAttachment>()
    }
}