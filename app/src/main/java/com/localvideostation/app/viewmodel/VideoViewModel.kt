package com.localvideostation.app.data

import android.content.Context

class VideoRepository(private val scanner: LocalVideoScanner? = null) {
    fun sampleVideos(): List<VideoEntity> = listOf(
        VideoEntity(
            id = 1,
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
            lastPlayedAt = System.currentTimeMillis() - 10_000,
            isVisible = true
        ),
        VideoEntity(
            id = 2,
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
            lastPlayedAt = System.currentTimeMillis() - 60_000,
            isVisible = true
        ),
        VideoEntity(
            id = 3,
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
            lastPlayedAt = System.currentTimeMillis() - 120_000,
            isVisible = true
        ),
        VideoEntity(
            id = 4,
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
            lastPlayedAt = System.currentTimeMillis() - 200_000,
            isVisible = true
        )
    )

    fun scanLocalVideos(context: Context): List<VideoEntity> {
        val scanned = scanner ?: LocalVideoScanner(context)
        return scanned.scanVideos().ifEmpty { sampleVideos() }
    }
}
