package link.limecode.reddit.lite.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import link.limecode.reddit.lite.config.CommonConstants.LOCAL_DATABASE_NAME
import link.limecode.reddit.lite.database.Database

class IOSSQLDelightDriverBuilder: SQLDelightDatabaseBuilder() {

    override fun buildDriver(): SqlDriver {
        return NativeSqliteDriver(Database.Schema, LOCAL_DATABASE_NAME)
    }
}