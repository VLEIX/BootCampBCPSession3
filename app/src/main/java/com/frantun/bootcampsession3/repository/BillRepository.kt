package com.frantun.bootcampsession3.repository

import com.frantun.bootcampsession3.db.dao.BillDao
import com.frantun.bootcampsession3.model.Bill
import kotlinx.coroutines.flow.Flow

class BillRepository(val billDao: BillDao) {

    val allBills: Flow<List<Bill>> = billDao.getBills()

    suspend fun insert(bill: Bill) {
        billDao.insert(bill)
    }

    suspend fun delete(bill: Bill) {
        billDao.delete(bill)
    }
}