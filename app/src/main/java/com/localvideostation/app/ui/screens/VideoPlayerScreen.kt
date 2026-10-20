package com.localvideostation.app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.localvideostation.app.data.RecommendationEngine
import com.localvideostation.app.data.VideoEntity
import com.localvideostation.app.data.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class VideoUiState(
    val videos: List<VideoEntity> = emptyList(),
    val favoriteVideos: List<VideoEntity> = emptyList(),
    val historyVideos: List<VideoEntity> = emptyList(),
    val recommendations: List<VideoEntity> = emptyList(),
    val searchQuery: String = "",
    val filteredVideos: List<VideoEntity> = emptyList(),
    val selectedVideoId: Long? = null
)

class VideoViewModel(
    private val repository: VideoRepository = VideoRepository()
) : ViewModel() {
    private val engine = RecommendationEngine()

    private val initialVideos = repository.sampleVideos()

    private val _uiState = MutableStateFlow(
        VideoUiState(
            videos = initialVideos,
            favoriteVideos = initialVideos.filter { it.favorite },
            historyVideos = initialVideos,
            recommendations = engine.recommend(initialVideos),
            filteredVideos = initialVideos
        )
    )
    val uiState: StateFlow<VideoUiState> = _uiState.asStateFlow()

    fun selectVideo(videoId: Long) {
        _uiState.value = _uiState.value.copy(selectedVideoId = videoId)
    }

    fun clearSelectedVideo() {
        _uiState.value = _uiState.value.copy(selectedVideoId = null)
    }

    fun scanLocalVideos(context: Context) {
        val scanned = repository.scanLocalVideos(context)
        val updated = if (scanned.isNotEmpty()) scanned else _uiState.value.videos
        _uiState.value = _uiState.value.copy(
            videos = updated,
            favoriteVideos = updated.filter { it.favorite },
            historyVideos = updated,
            recommendations = engine.recommend(updated),
            filteredVideos = if (_uiState.value.searchQuery.isBlank()) updated else updated.filter { video ->
                video.title.contains(_uiState.value.searchQuery, ignoreCase = true) ||
                    video.author.contains(_uiState.value.searchQuery, ignoreCase = true) ||
                    video.tags.any { tag -> tag.contains(_uiState.value.searchQuery, ignoreCase = true) }
            }
        )
    }

    fun toggleFavorite(videoId: Long) {
        val updated = _uiState.value.videos.map { video ->
            if (video.id == videoId) video.copy(favorite = !video.favorite) else video
        }
        _uiState.value = _uiState.value.copy(
            videos = updated,
            favoriteVideos = updated.filter { it.favorite },
            recommendations = engine.recommend(updated)
        )
    }

    fun rateVideo(videoId: Long, newRating: Float) {
        val updated = _uiState.value.videos.map { video ->
            if (video.id != videoId) return@map video
            val oldTotal = video.rating * video.ratingCount.toFloat()
            val nextCount = video.ratingCount + 1
            val nextAvg = if (nextCount == 0) newRating else (oldTotal + newRating) / nextCount.toFloat()
            video.copy(
                rating = nextAvg,
                ratingCount = nextCount
            )
        }
        _uiState.value = _uiState.value.copy(
            videos = updated,
            favoriteVideos = updated.filter { it.favorite },
            recommendations = engine.recommend(updated)
        )
    }

    fun markWatched(videoId: Long) {
        val updated = _uiState.value.videos.map { video ->
            if (video.id == videoId) {
                video.copy(lastPlayedAt = System.currentTimeMillis())
            } else {
                video
            }
        }
        _uiState.value = _uiState.value.copy(
            videos = updated,
            historyVideos = updated,
            recommendations = engine.recommend(updated)
        )
    }

    fun updateSearchQuery(query: String) {
        val q = query.trim()
        val filtered = if (q.isEmpty()) {
            _uiState.value.videos
        } else {
            _uiState.value.videos.filter { video ->
                video.title.contains(q, ignoreCase = true) ||
                    video.author.contains(q, ignoreCase = true) ||
                    video.tags.any { tag -> tag.contains(q, ignoreCase = true) }
            }
        }
        _uiState.value = _uiState.value.copy(
            searchQuery = q,
            filteredVideos = filtered
        )
    }
}
