package link.limecode.reddit.lite.domain.usecase

import io.github.jan.supabase.postgrest.query.Order
import link.limecode.reddit.lite.data.model.ApiPost
import link.limecode.reddit.lite.data.model.request.post.ApiReqPostListSort
import link.limecode.reddit.lite.data.model.response.post.ApiResPostListItem
import link.limecode.reddit.lite.domain.dao.PostDao
import link.limecode.reddit.lite.domain.dao.PostVoteDao

class PostUseCase(private val postDao: PostDao, private val postVoteDao: PostVoteDao) {
    
    suspend fun getPostList(
        userId: Long?,
        cursor: Long?,
        limit: Long,
        sort: ApiReqPostListSort
    ): List<ApiResPostListItem> {
        val order = when (sort) {
            ApiReqPostListSort.NEWEST -> Order.DESCENDING
            ApiReqPostListSort.OLDEST -> Order.ASCENDING
        }
        val postList = postDao.getPostList(cursor = cursor, limit = limit, order = order)
        
        return attachUserState(postList = postList, userId = userId)
    }
    
    suspend fun getPostListBy(
        subredditId: Long,
        userId: Long?,
        cursor: Long?,
        limit: Long,
        sort: ApiReqPostListSort
    ): List<ApiResPostListItem> {
        val order = when (sort) {
            ApiReqPostListSort.NEWEST -> Order.DESCENDING
            ApiReqPostListSort.OLDEST -> Order.ASCENDING
        }
        val postList = postDao.getPostListBy(subredditId = subredditId, cursor = cursor, limit = limit, order = order)
        
        return attachUserState(postList = postList, userId = userId)
    }
    
    private suspend fun attachUserState(postList: List<ApiPost>, userId: Long?): List<ApiResPostListItem> {
        val finalPostList = mutableListOf<ApiResPostListItem>()
        
        postList.forEach { post ->
            if (userId != null) {
                val voteResult = postVoteDao.getVote(userId = userId, postId = post.id)
                finalPostList.add(
                    ApiResPostListItem(
                        data = post,
                        userVote = voteResult?.voteType
                    )
                )
            } else {
                finalPostList.add(
                    ApiResPostListItem(
                        data = post,
                        userVote = null
                    )
                )
            }
        }
        
        return finalPostList.toList()
    }
}