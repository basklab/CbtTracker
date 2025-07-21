
package com.oleg.cbttracker

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): LocalDateTime? =
        value?.let { LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC) }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: LocalDateTime?): Long? =
        date?.toEpochSecond(ZoneOffset.UTC)

    @TypeConverter
    @JvmStatic
    fun fromUuid(uuid: String?): UUID? = uuid?.let { UUID.fromString(it) }

    @TypeConverter
    @JvmStatic
    fun uuidToString(uuid: UUID?): String? = uuid?.toString()
}

