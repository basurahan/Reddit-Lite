package link.limecode.reddit.lite.routes.post.handlers

import link.limecode.reddit.lite.data.model.request.post.ApiReqPostList
import link.limecode.reddit.lite.data.model.request.post.ApiReqPostListFilter
import link.limecode.reddit.lite.data.model.response.post.ApiResPostList
import link.limecode.reddit.lite.domain.usecase.PostUseCase
import link.limecode.reddit.lite.exceptions.UnexpectedDataException

suspend fun ApiReqPostList.handlePrivatePostList(
    postUseCase: PostUseCase,
    userId: Long
): ApiResPostList {
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
    }

    return ApiResPostList(
        items = result,
        cursor = result.lastOrNull()?.data?.id,
        limit = limit
    )
}

suspend fun ApiReqPostList.handlePublicPostList(postUseCase: PostUseCase): ApiResPostList {
    val result = when (this.filter) {
        ApiReqPostListFilter.ALL -> postUseCase.getPostList(
            userId = null,
            cursor = cursor,
            limit = limit,
            sort = sort
        )

        ApiReqPostListFilter.SUBREDDIT -> {
            if (this.subredditId == null) throw UnexpectedDataException()
            postUseCase.getPostListBy(
                subredditId = subredditId!!,
                userId = null,
                cursor = cursor,
                limit = limit,
                sort = sort
            )
        }
    }
    
    return ApiResPostList(
        items = result,
        cursor = result.lastOrNull()?.data?.id,
        limit = limit
    )
}