package com.localvideostation.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Query("SELECT * FROM videos ORDER BY lastPlayedAt DESC")
    fun observeVideos(): Flow<List<VideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(video: VideoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videos: List<VideoEntity>)

    @Query("SELECT * FROM videos WHERE favorite = 1 ORDER BY lastPlayedAt DESC")
    fun observeFavorites(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM videos WHERE watchedCount > 0 ORDER BY lastPlayedAt DESC")
    fun observeHistory(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM videos WHERE isVisible = 1 ORDER BY rating DESC, watchedCount DESC LIMIT 10")
    fun observeRecommended(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM videos WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): VideoEntity?

    @Update
    suspend fun update(video: VideoEntity)

    @Query("DELETE FROM videos WHERE id = :id")
    suspend fun deleteById(id: Long)
}
