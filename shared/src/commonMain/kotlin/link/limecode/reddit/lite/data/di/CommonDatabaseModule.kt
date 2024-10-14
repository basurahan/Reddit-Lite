package link.limecode.reddit.lite.data.di

import link.limecode.reddit.lite.database.Database
import link.limecode.reddit.lite.database.EntitySessionQueries
import org.koin.dsl.module

val commonDatabaseModule = module {
    single<EntitySessionQueries> { get<Database>().entitySessionQueries }
}