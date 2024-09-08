package link.limecode.reddit.lite.config

enum class Tables(val tableName: String) {
    USERS("users"),
    SUBREDDITS("subreddits"),
    SUBREDDIT_USERS("subreddit_users"),
    POSTS("posts"),
    POST_ATTACHMENTS("post_attachments"),
    POST_VOTES("post_votes")
}