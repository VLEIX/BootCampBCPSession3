package com.frantun.bootcampsession3.db.dao

import androidx.room.*
import com.frantun.bootcampsession3.model.Bill
import kotlinx.coroutines.flow.Flow

@Dao
interface BillDao {

    @Query("SELECT * FROM bill_table")
    fun getBills(): Flow<List<Bill>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bill: Bill)

    @Delete
    suspend fun delete(bill: Bill)
}
