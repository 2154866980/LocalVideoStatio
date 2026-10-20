package com.localvideostation.app.data

class RecommendationEngine {
    fun score(video: VideoEntity): Double {
        var total = 0.0
        total += video.rating * 2.2
        total += if (video.favorite) 15.0 else 0.0
        total += video.ratingCount * 0.8
        total += (video.lastPositionMs / 60000.0) * 0.1
        return total
    }

    fun recommend(videos: List<VideoEntity>): List<VideoEntity> {
        return videos.sortedByDescending { score(it) }.take(5)
    }
}
