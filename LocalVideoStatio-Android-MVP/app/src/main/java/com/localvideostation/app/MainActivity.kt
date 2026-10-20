package com.localvideostation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.localvideostation.app.ui.screens.HomeScreen
import com.localvideostation.app.ui.theme.LocalVideoStationTheme
import com.localvideostation.app.viewmodel.VideoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalVideoStationTheme {
                val viewModel: VideoViewModel = viewModel()
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}
