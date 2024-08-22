package com.example.transfer_speech_text

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.transfer_speech_text.presentation.navgraph.NavGraph
import com.example.transfer_speech_text.presentation.navgraph.Route
import com.example.transfer_speech_text.presentation.speech_to_text.SpeechToTextViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()) {
                NavGraph(destination = Route.HomeScreen.route)
            }
        }
    }
}