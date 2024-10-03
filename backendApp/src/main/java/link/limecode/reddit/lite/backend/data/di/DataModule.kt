package link.limecode.reddit.lite.backend.data.di

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.backend.data.db.Database
import link.limecode.reddit.lite.backend.data.db.dao.PostAttachmentDaoImpl
import link.limecode.reddit.lite.backend.data.db.dao.PostDaoImpl
import link.limecode.reddit.lite.backend.data.db.dao.PostVoteDaoImpl
import link.limecode.reddit.lite.backend.data.db.dao.SubRedditDaoImpl
import link.limecode.reddit.lite.backend.data.db.dao.SubRedditUsersDaoImpl
import link.limecode.reddit.lite.backend.data.db.dao.UsersDaoImpl
import link.limecode.reddit.lite.backend.domain.dao.PostAttachmentDao
import link.limecode.reddit.lite.backend.domain.dao.PostDao
import link.limecode.reddit.lite.backend.domain.dao.PostVoteDao
import link.limecode.reddit.lite.backend.domain.dao.SubRedditDao
import link.limecode.reddit.lite.backend.domain.dao.SubRedditUsersDao
import link.limecode.reddit.lite.backend.domain.dao.UsersDao
import link.limecode.reddit.lite.config.CommonTables
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ktor.plugin.RequestScope

val dataModule = module {
    single(createdAtStart = true) { Database() }

    single<PostgrestQueryBuilder>(named(CommonTables.USERS.tableName)) {
        get<Database>().client.from(CommonTables.USERS.tableName)
    }

    single<PostgrestQueryBuilder>(named(CommonTables.SUBREDDITS.tableName)) {
        get<Database>().client.from(CommonTables.SUBREDDITS.tableName)
    }

    single<PostgrestQueryBuilder>(named(CommonTables.SUBREDDIT_USERS.tableName)) {
        get<Database>().client.from(CommonTables.SUBREDDIT_USERS.tableName)
    }

    single<PostgrestQueryBuilder>(named(CommonTables.POSTS.tableName)) {
        get<Database>().client.from(CommonTables.POSTS.tableName)
    }

    single<PostgrestQueryBuilder>(named(CommonTables.POST_ATTACHMENTS.tableName)) {
        get<Database>().client.from(CommonTables.POST_ATTACHMENTS.tableName)
    }

    single<PostgrestQueryBuilder>(named(CommonTables.POST_VOTES.tableName)) {
        get<Database>().client.from(CommonTables.POST_VOTES.tableName)
    }

    scope<RequestScope> {
        scoped<UsersDao> {
            UsersDaoImpl(get<PostgrestQueryBuilder>(named(CommonTables.USERS.tableName)))
        }

        scoped<SubRedditDao> {
            SubRedditDaoImpl(get<PostgrestQueryBuilder>(named(CommonTables.SUBREDDITS.tableName)))
        }

        scoped<SubRedditUsersDao> {
            SubRedditUsersDaoImpl(get<PostgrestQueryBuilder>(named(CommonTables.SUBREDDIT_USERS.tableName)))
        }

        scoped<PostDao> {
            PostDaoImpl(get<PostgrestQueryBuilder>(named(CommonTables.POSTS.tableName)))
        }

        scoped<PostAttachmentDao> {
            PostAttachmentDaoImpl(get<PostgrestQueryBuilder>(named(CommonTables.POST_ATTACHMENTS.tableName)))
        }

        scoped<PostVoteDao> {
            PostVoteDaoImpl(get<PostgrestQueryBuilder>(named(CommonTables.POST_VOTES.tableName)))
        }
    }
}