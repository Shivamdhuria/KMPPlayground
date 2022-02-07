package me.abc.library.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

//actual class DatabaseDriverFactory(private val context: Context) {
//    actual fun createDriver(): SqlDriver {
//        return AndroidSqliteDriver(AppDatabase.Schema, context, "test.db")
//    }
//}
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        TODO("Not yet implemented")
    }
}