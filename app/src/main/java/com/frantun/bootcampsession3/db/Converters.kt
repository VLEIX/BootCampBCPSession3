package com.frantun.bootcampsession3.db

import androidx.room.TypeConverter
import com.frantun.bootcampsession3.model.BillType
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.let { it?.time }
    }

    @TypeConverter
    fun fromBillTypeOrdinal(index: Int?): BillType? {
        return index?.let {
            when (it) {
                BillType.STUDIES.ordinal -> BillType.STUDIES
                BillType.FOOD.ordinal -> BillType.FOOD
                BillType.WORK.ordinal -> BillType.WORK
                BillType.OTHER.ordinal -> BillType.OTHER
                else -> BillType.OTHER
            }
        }
    }

    @TypeConverter
    fun billTypeToOrdinal(billType: BillType?): Int? {
        return billType?.ordinal
    }
}