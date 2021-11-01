package com.barryalan.randomassignerkmm.datasource.cache

import com.barryalan.randomassignerkmm.datasource.cache.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema,"appDatabase.db")
    }
}