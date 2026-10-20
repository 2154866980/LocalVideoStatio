package com.localvideostation.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.localvideostation.app.data.VideoEntity

@Composable
fun SearchScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    videos: List<VideoEntity>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("搜索视频 / 作者 / 标签") },
            modifier = Modifier.fillMaxWidth()
        )

        if (videos.isNotEmpty()) {
            Column(modifier = Modifier.padding(top = 12.dp)) {
                videos.forEach { video ->
                    Text(
                        text = "- ${video.title} / ${video.author}",
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}
