package link.limecode.reddit.lite.data.db.dao

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.data.model.ApiPostVote
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost
import link.limecode.reddit.lite.domain.dao.PostVoteDao

class PostVoteDaoImpl(private val table: PostgrestQueryBuilder): PostVoteDao {

    override suspend fun insert(vote: ApiReqVotePost): ApiPostVote {
        return table.insert(vote){ select() }.decodeSingle<ApiPostVote>()
    }
}