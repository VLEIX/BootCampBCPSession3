package com.frantun.bootcampsession3.main

import androidx.lifecycle.*
import com.frantun.bootcampsession3.model.Bill
import com.frantun.bootcampsession3.repository.BillRepository
import kotlinx.coroutines.launch

class MainViewModel(val billRepository: BillRepository) : ViewModel() {

    val allBills: LiveData<List<Bill>> = billRepository.allBills.asLiveData()

    fun delete(bill: Bill) {
        viewModelScope.launch {
            billRepository.delete(bill)
        }
    }
}

class MainViewModelFactory(val billRepository: BillRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(billRepository) as T
        }
        throw IllegalArgumentException("")
    }
}