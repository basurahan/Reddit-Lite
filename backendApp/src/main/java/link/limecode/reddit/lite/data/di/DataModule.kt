package link.limecode.reddit.lite.data.di

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.config.CommonTables
import link.limecode.reddit.lite.data.db.Database
import link.limecode.reddit.lite.data.db.dao.*
import link.limecode.reddit.lite.domain.dao.*
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

        scoped<PostAttachementDao> {
            PostAttachmentDaoImpl(get<PostgrestQueryBuilder>(named(CommonTables.POST_ATTACHMENTS.tableName)))
        }

        scoped<PostVoteDao> {
            PostVoteDaoImpl(get<PostgrestQueryBuilder>(named(CommonTables.POST_VOTES.tableName)))
        }
    }
}