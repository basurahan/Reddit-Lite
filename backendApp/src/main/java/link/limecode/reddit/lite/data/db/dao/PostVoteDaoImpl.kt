package link.limecode.reddit.lite.data.db.dao

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.data.model.ApiPostVote
import link.limecode.reddit.lite.data.model.ApiVotePost
import link.limecode.reddit.lite.data.model.ApiVoteType
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost
import link.limecode.reddit.lite.domain.dao.PostVoteDao

class PostVoteDaoImpl(private val table: PostgrestQueryBuilder): PostVoteDao {

    override suspend fun insert(vote: ApiReqVotePost): ApiPostVote {
        return table.insert(vote){ select() }.decodeSingle<ApiPostVote>()
    }

    override suspend fun insert(vote: ApiVotePost): ApiPostVote {
        return table.insert(vote){ select() }.decodeSingle<ApiPostVote>()
    }

    override suspend fun isUpVotedBy(userId: Long, postId: Long): Boolean {
        return table.select {
            filter {
                ApiPostVote::postId eq postId
                ApiPostVote::userId eq userId
                ApiPostVote::voteType eq ApiVoteType.UPVOTE
            }
        }.decodeSingleOrNull<ApiPostVote>() != null
    }

    override suspend fun isDownVotedBy(userId: Long, postId: Long): Boolean {
        return table.select {
            filter {
                ApiPostVote::postId eq postId
                ApiPostVote::userId eq userId
                ApiPostVote::voteType eq ApiVoteType.DOWNVOTE
            }
        }.decodeSingleOrNull<ApiPostVote>() != null
    }

    override suspend fun getVote(userId: Long, postId: Long): ApiPostVote? {
        return table.select {
            filter {
                ApiPostVote::postId eq postId
                ApiPostVote::userId eq userId
            }
        }.decodeSingleOrNull<ApiPostVote>()
    }

    override suspend fun updateVote(userId: Long, postId: Long, newVote: ApiVoteType): ApiPostVote {
        return table.update(
            {
                ApiPostVote::voteType setTo newVote
            }
        ) {
            select()
            filter {
                ApiPostVote::postId eq postId
                ApiPostVote::userId eq userId
            }
        }.decodeSingle<ApiPostVote>()
    }
}