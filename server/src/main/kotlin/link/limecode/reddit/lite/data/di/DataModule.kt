package link.limecode.reddit.lite.data.di

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.config.Tables
import link.limecode.reddit.lite.data.db.Database
import link.limecode.reddit.lite.data.db.dao.PostDaoImpl
import link.limecode.reddit.lite.data.db.dao.SubRedditUsersDaoImpl
import link.limecode.reddit.lite.data.db.dao.SubRedditDaoImpl
import link.limecode.reddit.lite.domain.dao.UsersDao
import link.limecode.reddit.lite.data.db.dao.UsersDaoImpl
import link.limecode.reddit.lite.domain.dao.PostDao
import link.limecode.reddit.lite.domain.dao.SubRedditDao
import link.limecode.reddit.lite.domain.dao.SubRedditUsersDao
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
    }
}