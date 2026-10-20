package com.localvideostation.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.localvideostation.app.data.VideoEntity

@Composable
fun VideoPlayerScreen(
    video: VideoEntity,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1117))
            .padding(24.dp)
    ) {
        Column {
            Text(text = video.title, color = Color.White)
            Text(
                text = "UP：${video.author}",
                color = Color(0xFFB8C2D1),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "时长：${video.durationText} • 评分：${video.rating}",
                color = Color(0xFFB8C2D1),
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = onBack,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text("返回列表")
            }
        }
    }
}
