package link.limecode.reddit.lite.domain.usecase

import link.limecode.reddit.lite.data.model.ApiPost
import link.limecode.reddit.lite.data.model.ApiPostVote
import link.limecode.reddit.lite.data.model.ApiVoteType
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost
import link.limecode.reddit.lite.domain.dao.PostDao
import link.limecode.reddit.lite.domain.dao.PostVoteDao

class VoteUseCase(
    private val postVoteDao: PostVoteDao,
    private val postDao: PostDao
) {
    
    suspend fun upVote(post: ApiPost, userId: Int): ApiPost {
        val voteData = postVoteDao.getVote(userId = userId, postId = post.id)
        
        // if already upvoted then no need to do anything
        if (voteData?.voteType == ApiVoteType.UPVOTE) {
            return post
        } else if (voteData?.voteType == ApiVoteType.DOWNVOTE) {
            postVoteDao.updateVote(userId = userId, postId = post.id, newVote = ApiVoteType.UPVOTE)
            
            val currentDownVoteCount = postDao.getDownVoteCount(post.id)
            val currentUpVoteCount = postDao.getUpVoteCount(post.id)
            postDao.decrementDownVote(id = post.id, currentCount = currentDownVoteCount)
            postDao.incrementUpVote(id = post.id, currentCount = currentUpVoteCount)
            return postDao.getPostBy(post.id)
        }
        
        postVoteDao.insert(ApiReqVotePost(userId = userId, postId = post.id, voteType = ApiVoteType.UPVOTE))
        
        val currentUpVoteCount = postDao.getUpVoteCount(post.id)
        postDao.incrementUpVote(id = post.id, currentCount = currentUpVoteCount)
        return postDao.getPostBy(post.id)
    }
    
    suspend fun downVote(post: ApiPost, userId: Int): ApiPost {
        val voteData = postVoteDao.getVote(userId = userId, postId = post.id)
        
        // if already upvoted then no need to do anything
        if (voteData?.voteType == ApiVoteType.DOWNVOTE) {
            return post
        } else if (voteData?.voteType == ApiVoteType.UPVOTE) {
            postVoteDao.updateVote(userId = userId, postId = post.id, newVote = ApiVoteType.UPVOTE)
            
            val currentDownVoteCount = postDao.getDownVoteCount(post.id)
            val currentUpVoteCount = postDao.getUpVoteCount(post.id)
            postDao.decrementUpVote(id = post.id, currentCount = currentUpVoteCount)
            postDao.incrementDownVote(id = post.id, currentCount = currentDownVoteCount)
            return postDao.getPostBy(post.id)
        }
        
        val currentDownVoteCount = postDao.getDownVoteCount(post.id)
        postDao.incrementDownVote(id = post.id, currentCount = currentDownVoteCount)
        postVoteDao.insert(ApiReqVotePost(userId = userId, postId = post.id, voteType = ApiVoteType.DOWNVOTE))
        
        return postDao.getPostBy(post.id)
    }
}