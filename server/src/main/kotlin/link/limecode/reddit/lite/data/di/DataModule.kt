package link.limecode.reddit.lite.data.di

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.config.Tables
import link.limecode.reddit.lite.data.db.Database
import link.limecode.reddit.lite.data.db.dao.*
import link.limecode.reddit.lite.domain.dao.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ktor.plugin.RequestScope

val dataModule = module {
    single(createdAtStart = true) { Database() }

    single<PostgrestQueryBuilder>(named(Tables.USERS.tableName)) {
        get<Database>().client.from(Tables.USERS.tableName)
    }

    single<PostgrestQueryBuilder>(named(Tables.SUBREDDITS.tableName)) {
        get<Database>().client.from(Tables.SUBREDDITS.tableName)
    }

    single<PostgrestQueryBuilder>(named(Tables.SUBREDDIT_USERS.tableName)) {
        get<Database>().client.from(Tables.SUBREDDIT_USERS.tableName)
    }

    single<PostgrestQueryBuilder>(named(Tables.POSTS.tableName)) {
        get<Database>().client.from(Tables.POSTS.tableName)
    }

    single<PostgrestQueryBuilder>(named(Tables.POST_ATTACHMENTS.tableName)) {
        get<Database>().client.from(Tables.POST_ATTACHMENTS.tableName)
    }

    scope<RequestScope> {
        scoped<UsersDao> {
            UsersDaoImpl(get<PostgrestQueryBuilder>(named(Tables.USERS.tableName)))
        }

        scoped<SubRedditDao> {
            SubRedditDaoImpl(get<PostgrestQueryBuilder>(named(Tables.SUBREDDITS.tableName)))
        }

        scoped<SubRedditUsersDao> {
            SubRedditUsersDaoImpl(get<PostgrestQueryBuilder>(named(Tables.SUBREDDIT_USERS.tableName)))
        }

        scoped<PostDao> {
            PostDaoImpl(get<PostgrestQueryBuilder>(named(Tables.POSTS.tableName)))
        }

        scoped<PostAttachementDao> {
            PostAttachmentDaoImpl(get<PostgrestQueryBuilder>(named(Tables.POST_ATTACHMENTS.tableName)))
        }
    }
}