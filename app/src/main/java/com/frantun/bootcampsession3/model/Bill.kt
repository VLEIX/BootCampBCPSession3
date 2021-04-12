package com.frantun.bootcampsession3.model

import java.io.Serializable
import java.util.*

enum class BillType {
    STUDIES,
    FOOD,
    WORK,
    OTHER
}

data class Bill(
    val description: String,
    val amount: Double,
    val date: Date,
    val type: BillType
) : Serializable