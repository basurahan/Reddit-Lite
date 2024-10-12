package link.limecode.reddit.lite.data.di

import link.limecode.reddit.lite.data.database.IOSSQLDelightDriverBuilder
import link.limecode.reddit.lite.database.Database
import org.koin.dsl.module

val iOSDatabaseModule = module {
    single<Database> { IOSSQLDelightDriverBuilder().createDatabase() }
}