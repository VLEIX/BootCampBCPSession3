package com.frantun.bootcampsession3.add

import androidx.lifecycle.*
import com.frantun.bootcampsession3.model.Bill
import com.frantun.bootcampsession3.repository.BillRepository
import kotlinx.coroutines.launch

class AddBillViewModel(val billRepository: BillRepository) : ViewModel() {
    fun insert(bill: Bill) {
        viewModelScope.launch {
            billRepository.insert(bill)
        }
    }
}

class AddBillViewModelFactory(val billRepository: BillRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddBillViewModel::class.java)) {
            return AddBillViewModel(billRepository) as T
        }
        throw IllegalArgumentException("")
    }
}