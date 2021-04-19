package com.frantun.bootcampsession3.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.frantun.bootcampsession3.db.Converters
import com.frantun.bootcampsession3.db.dao.BillDao
import com.frantun.bootcampsession3.model.Bill

@Database(entities = [Bill::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
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
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}