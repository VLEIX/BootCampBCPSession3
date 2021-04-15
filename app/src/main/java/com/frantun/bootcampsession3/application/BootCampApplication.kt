package com.frantun.bootcampsession3.application

import android.app.Application
import com.frantun.bootcampsession3.db.database.BootCampRoomDatabase
import com.frantun.bootcampsession3.repository.BillRepository

class BootCampApplication : Application() {
    val database by lazy { BootCampRoomDatabase.getDatabase(this) }
    val billRepository by lazy { BillRepository(database.billDao()) }
}