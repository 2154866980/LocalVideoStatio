package com.localvideostation.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LibraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "本地视频库", color = Color.White)
        Text(
            text = "支持扫描目录、分类和筛选",
            color = Color(0xFFB8C2D1),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
