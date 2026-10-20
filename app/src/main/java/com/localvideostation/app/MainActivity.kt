package com.localvideostation.app

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.localvideostation.app.ui.theme.LocalVideoStationTheme
import com.localvideostation.app.viewmodel.VideoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            LocalVideoStationTheme {
                LocalVideoStationApp()
            }
        }
    }
}

@Composable
fun LocalVideoStationApp() {
    val viewModel = androidx.lifecycle.viewmodel.compose.viewModel<VideoViewModel>()
    HomeScreen(viewModel = viewModel)
}
