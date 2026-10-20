package com.localvideostation.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val fileName: String,
    val path: String,
    val author: String,
    val durationMs: Long,
    val rating: Float = 0f,
    val ratingCount: Int = 0,
    val tags: List<String> = emptyList(),
    val favorite: Boolean = false,
    val lastPositionMs: Long = 0L,
    val lastPlayedAt: Long = 0L,
    val watchedCount: Int = 0,
    val isVisible: Boolean = true
) {
    val durationText: String
        get() = formatDuration(durationMs)

    companion object {
        fun formatDuration(durationMs: Long): String {
            val totalSeconds = (durationMs / 1000L).coerceAtLeast(0)
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            return "%02d:%02d".format(minutes, seconds)
        }
    }
}
