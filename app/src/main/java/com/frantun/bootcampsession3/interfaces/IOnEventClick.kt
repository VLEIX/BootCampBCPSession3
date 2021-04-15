package com.frantun.bootcampsession3.interfaces

import com.frantun.bootcampsession3.model.Bill

interface IOnEventClick {
    fun onClick(bill: Bill)
}