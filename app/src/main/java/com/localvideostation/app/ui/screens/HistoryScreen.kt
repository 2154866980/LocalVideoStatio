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
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "最近观看", color = Color.White)
        Text(
            text = "根据观看历史与活跃度参与推荐",
            color = Color(0xFFB8C2D1),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
