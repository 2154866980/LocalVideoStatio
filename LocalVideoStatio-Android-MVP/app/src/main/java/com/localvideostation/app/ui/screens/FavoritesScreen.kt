package com.localvideostation.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.localvideostation.app.data.VideoEntity

@Composable
fun FavoritesScreen(videos: List<VideoEntity>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "收藏列表", color = Color.White)
        if (videos.isEmpty()) {
            Text(
                text = "还没有收藏的视频",
                color = Color(0xFFB8C2D1),
                modifier = Modifier.padding(top = 12.dp)
            )
        } else {
            videos.forEach { video ->
                Text(
                    text = "- ${video.title} • ${video.author}",
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
