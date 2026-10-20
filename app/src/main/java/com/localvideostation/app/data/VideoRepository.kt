package com.localvideostation.app.data

import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore

class LocalVideoScanner(private val context: Context) {
    fun scanVideos(): List<VideoEntity> {
        val videos = mutableListOf<VideoEntity>()

        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATE_ADDED
        )

        val sortOrder = "${MediaStore.Video.Media.DATE_ADDED} DESC"

        context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn) ?: "未命名视频"
                val path = cursor.getString(dataColumn) ?: ""
                val durationMs = cursor.getLong(durationColumn)
                val uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)

                videos += VideoEntity(
                    id = id,
                    title = name.removeSuffix(".mp4").removeSuffix(".mkv").removeSuffix(".avi").removeSuffix(".mov")
                        .ifBlank { "本地视频" },
                    fileName = name,
                    path = path.ifBlank { uri.toString() },
                    author = "本地UP",
                    durationMs = durationMs.coerceAtLeast(0L),
                    rating = 4.0f,
                    ratingCount = 0,
                    tags = listOf("本地", "扫描"),
                    favorite = false,
                    lastPositionMs = 0L,
                    lastPlayedAt = System.currentTimeMillis(),
                    isVisible = true
                )
            }
        }

        return videos
    }
}
