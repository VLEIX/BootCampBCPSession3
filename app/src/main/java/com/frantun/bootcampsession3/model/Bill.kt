package com.frantun.bootcampsession3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

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
    val amount: Double
) : Serializable