package link.limecode.reddit.lite.routes.post.handlers

import link.limecode.reddit.lite.data.model.request.post.ApiReqAuthPostList
import link.limecode.reddit.lite.data.model.request.post.ApiReqPostListFilter
import link.limecode.reddit.lite.data.model.response.post.ApiResAuthPostList
import link.limecode.reddit.lite.domain.usecase.PostUseCase
import link.limecode.reddit.lite.exceptions.UnexpectedDataException

suspend fun ApiReqAuthPostList.handleAuthPostList(
    postUseCase: PostUseCase,
    userId: Int
): ApiResAuthPostList {
    val result = when (this.filter) {
        ApiReqPostListFilter.ALL -> postUseCase.getPostList(
            userId = userId,
            cursor = cursor,
            limit = limit,
            sort = sort
        )
    
        ApiReqPostListFilter.SUBREDDIT -> {
            if (this.subredditId == null) throw UnexpectedDataException()
            postUseCase.getPostListBy(
                subredditId = subredditId!!,
                userId = userId,
                cursor = cursor,
                limit = limit,
                sort = sort
            )
        }
    
        ApiReqPostListFilter.POPULAR -> TODO()
    }
    
    return ApiResAuthPostList(
        post = result,
        cursor = result.lastOrNull()?.data?.id,
        limit = limit
    )
}