package com.localvideostation.app.data

class RecommendationEngine {
    fun score(video: VideoEntity): Double {
        val ratingWeight = video.rating * 2.5
        val favoriteWeight = if (video.favorite) 18.0 else 0.0
        val watchWeight = video.watchedCount * 4.0
        val recencyWeight = if (video.lastPlayedAt > 0L) {
            val hoursAgo = (System.currentTimeMillis() - video.lastPlayedAt) / 3_600_000.0
            maxOf(0.0, 10.0 - hoursAgo * 0.6)
        } else {
            0.0
        }
        val progressWeight = (video.lastPositionMs / 60000.0) * 0.3
        return ratingWeight + favoriteWeight + watchWeight + recencyWeight + progressWeight
    }

    fun recommend(videos: List<VideoEntity>): List<VideoEntity> {
        return videos.sortedByDescending { score(it) }.take(8)
    }
}
