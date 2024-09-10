package link.limecode.reddit.lite.data.db.dao

import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.data.model.ApiPost
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPost
import link.limecode.reddit.lite.domain.dao.PostDao

class PostDaoImpl(private val table: PostgrestQueryBuilder) : PostDao {
    
    override suspend fun insert(post: ApiReqNewPost): ApiPost {
        return table.insert(post) { select() }.decodeSingle<ApiPost>()
    }

    override suspend fun incrementUpVote(id: Int, currentCount: Int): ApiPost {
        return  table.update(
            {
                ApiPost::upvoteCount setTo currentCount + 1
            }
        ) {
            select()
            filter {
                ApiPost::id eq id
            }
        }.decodeSingle<ApiPost>()
    }

    override suspend fun incrementDownVote(id: Int, currentCount: Int): ApiPost {
        return  table.update(
            {
                ApiPost::downvoteCount setTo currentCount + 1
            }
        ) {
            select()
            filter {
                ApiPost::id eq id
            }
        }.decodeSingle<ApiPost>()
    }

    override suspend fun decrementUpVote(id: Int, currentCount: Int): ApiPost {
        return  table.update(
            {
                ApiPost::upvoteCount setTo currentCount - 1
            }
        ) {
            select()
            filter {
                ApiPost::id eq id
            }
        }.decodeSingle<ApiPost>()
    }

    override suspend fun decrementDownVote(id: Int, currentCount: Int): ApiPost {
        return  table.update(
            {
                ApiPost::downvoteCount setTo currentCount - 1
            }
        ) {
            select()
            filter {
                ApiPost::id eq id
            }
        }.decodeSingle<ApiPost>()
    }

    override suspend fun getUpVoteCount(id: Int): Int {
        return table.select {
            filter {
                ApiPost::id eq id
            }
        }.decodeSingle<ApiPost>().upvoteCount
    }

    override suspend fun getDownVoteCount(id: Int): Int {
        return table.select {
            filter {
                ApiPost::id eq id
            }
        }.decodeSingle<ApiPost>().downvoteCount
    }

    override suspend fun getPostBy(id: Int): ApiPost {
        return table.select {
            filter {
                ApiPost::id eq id
            }
        }.decodeSingle<ApiPost>()
    }

    override suspend fun getPostList(cursor: Int?, limit: Long, order: Order): List<ApiPost> {
        return table.select {
            // TODO: is there a better way than using string literal
            order(column = "created_at", order = order)
            filter {
                if (cursor == null) return@filter

                if (order == Order.DESCENDING) {
                    ApiPost::id lt cursor
                } else if (order == Order.ASCENDING) {
                    ApiPost::id gt cursor
                }
            }
            limit(count = limit)
        }.decodeAs<List<ApiPost>>()
    }

    override suspend fun getPostListBy(
        subredditId: Int,
        cursor: Int?,
        limit: Long,
        order: Order
    ): List<ApiPost> {
        return table.select {
            // TODO: is there a better way than using string literal
            order(column = "created_at", order = order)
            filter {
                ApiPost::subredditId eq subredditId

                if (cursor == null) return@filter

                if (order == Order.DESCENDING) {
                    ApiPost::id lt cursor
                } else if (order == Order.ASCENDING) {
                    ApiPost::id gt cursor
                }
            }
            limit(count = limit)
        }.decodeAs<List<ApiPost>>()
    }
}