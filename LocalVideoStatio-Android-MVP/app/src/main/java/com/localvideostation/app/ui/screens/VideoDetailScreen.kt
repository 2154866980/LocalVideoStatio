package com.localvideostation.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.localvideostation.app.data.VideoEntity

@Composable
fun VideoDetailScreen(
    video: VideoEntity,
    onBack: () -> Unit,
    onRate: (Float) -> Unit,
    onMarkWatched: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1117))
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = video.title,
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1B2331))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "UP：${video.author}", color = Color.White)
                    Text(text = "时长：${video.durationText}", color = Color(0xFFB8C2D1), modifier = Modifier.padding(top = 8.dp))
                    Text(text = "评分：${video.rating}/5  •  评分次数：${video.ratingCount}", color = Color(0xFFFFD166), modifier = Modifier.padding(top = 8.dp))
                    Text(text = "标签：${video.tags.joinToString { "#${it}" }}", color = Color(0xFF78B8FF), modifier = Modifier.padding(top = 8.dp))
                    Text(text = "收藏：${if (video.favorite) "已收藏" else "未收藏"}", color = Color(0xFFB8C2D1), modifier = Modifier.padding(top = 8.dp))
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(1f, 2f, 3f, 4f, 5f).forEach { star ->
                    Button(
                        onClick = { onRate(star) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (video.rating >= star) Color(0xFFFFD166) else Color(0xFF2D3645)
                        )
                    ) {
                        Text(text = star.toInt().toString(), color = Color.White)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = onMarkWatched) {
                    Text("标记已观看")
                }
                Button(onClick = onBack) {
                    Text("返回列表")
                }
            }
        }
    }
}
