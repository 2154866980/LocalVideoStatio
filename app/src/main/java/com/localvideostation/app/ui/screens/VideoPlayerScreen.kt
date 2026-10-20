package com.localvideostation.app.viewmodel

import androidx.lifecycle.ViewModel
import com.localvideostation.app.data.RecommendationEngine
import com.localvideostation.app.data.VideoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VideoViewModel : ViewModel() {

    private val engine = RecommendationEngine()

    private val _videos = MutableStateFlow(dummyVideos())
    val videos: StateFlow<List<VideoEntity>> = _videos.asStateFlow()

    val recommendations: StateFlow<List<VideoEntity>> = MutableStateFlow(
        engine.recommend(dummyVideos())
    ).asStateFlow()

    private companion object {
        fun dummyVideos(): List<VideoEntity> = listOf(
            VideoEntity(
                title = "少女与荒野",
                fileName = "shou_nv.mp4",
                path = "/storage/emulated/0/Videos/shou_nv.mp4",
                author = "沉海UP",
                durationMs = 3_400_000,
                rating = 4.8f,
                ratingCount = 192,
                tags = listOf("治愈", "旅行", "自然"),
                favorite = true,
                lastPositionMs = 90_000,
                lastPlayedAt = System.currentTimeMillis() - 10_000
            ),
            VideoEntity(
                title = "弹丸之城",
                fileName = "dan_ju.mp4",
                path = "/storage/emulated/0/Videos/dan_ju.mp4",
                author = "泽野工作室",
                durationMs = 2_100_000,
                rating = 4.6f,
                ratingCount = 84,
                tags = listOf("剧情", "动作", "都市"),
                favorite = false,
                lastPositionMs = 120_000,
                lastPlayedAt = System.currentTimeMillis() - 60_000
            ),
            VideoEntity(
                title = "夜航日志",
                fileName = "night_flight.mp4",
                path = "/storage/emulated/0/Videos/night_flight.mp4",
                author = "月光档案",
                durationMs = 4_800_000,
                rating = 4.7f,
                ratingCount = 156,
                tags = listOf("电影", "夜晚", "纪录片"),
                favorite = true,
                lastPositionMs = 304_000,
                lastPlayedAt = System.currentTimeMillis() - 120_000
            ),
            VideoEntity(
                title = "夏日打工记",
                fileName = "summer_job.mp4",
                path = "/storage/emulated/0/Videos/summer_job.mp4",
                author = "茶树君",
                durationMs = 2_700_000,
                rating = 4.3f,
                ratingCount = 48,
                tags = listOf("生活", "搞笑"),
                favorite = false,
                lastPositionMs = 15_000,
                lastPlayedAt = System.currentTimeMillis() - 200_000
            )
        )
    }
}
