package link.limecode.reddit.lite.data.database

import app.cash.sqldelight.db.SqlDriver
import link.limecode.reddit.lite.database.Database

abstract class SQLDelightDatabaseBuilder {
    abstract fun buildDriver(): SqlDriver

    fun createDatabase(): Database {
        return Database(buildDriver())
    }
}