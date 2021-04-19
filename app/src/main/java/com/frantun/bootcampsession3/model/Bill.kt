package com.frantun.bootcampsession3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

enum class BillType {
    STUDIES,
    FOOD,
    WORK,
    OTHER
}

@Entity(tableName = "bill_table")
data class Bill(
    @PrimaryKey
    val description: String,
    val amount: Double,
    val date: Date,
    val type: BillType
) : Serializable