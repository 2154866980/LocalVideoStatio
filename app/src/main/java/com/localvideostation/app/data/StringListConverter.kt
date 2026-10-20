package com.localvideostation.app.data

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> =
        if (value.isBlank()) emptyList() else value.split(",").map { it.trim() }.filter { it.isNotEmpty() }

    @TypeConverter
    fun fromList(value: List<String>): String = value.joinToString(separator = ",")
}
