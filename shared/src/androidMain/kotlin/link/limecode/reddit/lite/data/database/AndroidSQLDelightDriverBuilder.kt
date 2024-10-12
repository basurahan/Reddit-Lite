package link.limecode.reddit.lite.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import link.limecode.reddit.lite.config.CommonConstants.LOCAL_DATABASE_NAME
import link.limecode.reddit.lite.database.Database

class AndroidSQLDelightDriverBuilder(private val context: Context): SQLDelightDatabaseBuilder() {

    override fun buildDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, LOCAL_DATABASE_NAME)
    }
}