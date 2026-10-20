package com.localvideostation.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.localvideostation.app.data.VideoEntity
import com.localvideostation.app.viewmodel.VideoViewModel

@Composable
fun HomeScreen(viewModel: VideoViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("首页", "收藏", "历史", "搜索")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F1117),
                        Color(0xFF121822),
                        Color(0xFF171B27)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "LocalVideoStation",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD166),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "4.8",
                        color = Color(0xFFFFD166),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(tabs) { tab ->
                    val selected = tab == tabs[selectedTab]
                    Card(
                        onClick = { selectedTab = tabs.indexOf(tab) },
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (selected) Color(0xFF2B6BFF) else Color(0xFF1E2430)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val icon = when (tab) {
                                "首页" -> Icons.Default.Home
                                "收藏" -> Icons.Default.Favorite
                                "历史" -> Icons.Default.History
                                else -> Icons.Default.Search
                            }
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = tab,
                                color = Color.White,
                                modifier = Modifier.padding(start = 8.dp),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            when (selectedTab) {
                1 -> FavoritesScreen(uiState.favoriteVideos)
                2 -> HistoryScreen(uiState.historyVideos)
                3 -> SearchScreen(
                    query = uiState.searchQuery,
                    onQueryChange = { viewModel.updateSearchQuery(it) },
                    videos = uiState.filteredVideos
                )
                else -> {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.videos) { video ->
                                VideoCard(video = video) { viewModel.toggleFavorite(video.id) }
                            }
                        }

                        Column(
                            modifier = Modifier.width(280.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "推荐",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            uiState.recommendations.forEach { video ->
                                RecommendationCard(video = video)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VideoCard(
    video: VideoEntity,
    onToggleFavorite: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C2230)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = video.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "${video.rating}/5",
                    color = Color(0xFFFFD166),
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "UP: ${video.author} • ${video.durationText}",
                color = Color(0xFFB8C2D1),
                modifier = Modifier.padding(top = 6.dp)
            )

            Text(
                text = video.tags.joinToString(separator = " • ") { "#${it}" },
                color = Color(0xFF78B8FF),
                modifier = Modifier.padding(top = 10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF2B6BFF)),
                    onClick = onToggleFavorite
                ) {
                    Text(
                        text = if (video.favorite) "已收藏" else "收藏",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun RecommendationCard(video: VideoEntity) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF202838)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = video.title,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "评分 ${video.rating} • 收藏 ${video.favorite}",
                color = Color(0xFFB8C2D1),
                modifier = Modifier.padding(top = 6.dp)
            )
            Text(
                text = video.tags.joinToString { "#${it}" },
                color = Color(0xFF78B8FF),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
