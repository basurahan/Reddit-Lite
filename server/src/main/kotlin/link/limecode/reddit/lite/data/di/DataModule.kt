package link.limecode.reddit.lite.data.di

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.config.Tables
import link.limecode.reddit.lite.data.db.Database
import link.limecode.reddit.lite.data.db.subreddits.SubRedditDaoImpl
import link.limecode.reddit.lite.domain.dao.UsersDao
import link.limecode.reddit.lite.data.db.users.UsersDaoImpl
import link.limecode.reddit.lite.domain.dao.SubRedditDao
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

    scope<RequestScope> {
        scoped<UsersDao> {
            UsersDaoImpl(get<PostgrestQueryBuilder>(named(Tables.USERS.tableName)))
        }

        scoped<SubRedditDao> {
            SubRedditDaoImpl(get<PostgrestQueryBuilder>(named(Tables.SUBREDDITS.tableName)))
        }
    }
}