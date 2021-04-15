package com.frantun.bootcampsession3.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.frantun.bootcampsession3.db.dao.BillDao
import com.frantun.bootcampsession3.model.Bill

@Database(entities = [Bill::class], version = 1)
abstract class BootCampRoomDatabase : RoomDatabase() {

    abstract fun billDao(): BillDao

    companion object {
        private var INSTANCE: BootCampRoomDatabase? = null

        fun getDatabase(context: Context): BootCampRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BootCampRoomDatabase::class.java,
                    "boot_camp_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}