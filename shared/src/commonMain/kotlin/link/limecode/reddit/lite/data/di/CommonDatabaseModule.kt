package link.limecode.reddit.lite.data.di

import link.limecode.reddit.lite.database.Database
import link.limecode.reddit.lite.database.SessionQueries
import org.koin.dsl.module

val commonDatabaseModule = module {
    single<SessionQueries> { get<Database>().sessionQueries }
}