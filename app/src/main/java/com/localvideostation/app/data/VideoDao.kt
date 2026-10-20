package com.localvideostation.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Query("SELECT * FROM videos ORDER BY lastPlayedAt DESC")
    fun observeVideos(): Flow<List<VideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(video: VideoEntity)

    @Query("SELECT * FROM videos WHERE favorite = 1 ORDER BY lastPlayedAt DESC")
    fun observeFavorites(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM videos WHERE isVisible = 1 ORDER BY rating DESC")
    fun observeRecommended(): Flow<List<VideoEntity>>
}
