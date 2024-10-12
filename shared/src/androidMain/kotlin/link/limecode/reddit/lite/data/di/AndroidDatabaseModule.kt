package link.limecode.reddit.lite.data.di

import link.limecode.reddit.lite.data.database.AndroidSQLDelightDriverBuilder
import link.limecode.reddit.lite.database.Database
import link.limecode.reddit.lite.database.SessionQueries
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val androidDatabaseModule = module {
    single<Database>(createdAtStart = true) { AndroidSQLDelightDriverBuilder(androidApplication().applicationContext).createDatabase() }
}