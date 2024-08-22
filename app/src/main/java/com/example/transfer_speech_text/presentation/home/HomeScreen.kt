package com.example.transfer_speech_text.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.transfer_speech_text.presentation.navgraph.Route
import com.example.transfer_speech_text.presentation.speech_to_text.SpeechToTextViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { navController.navigate(Route.SpeechToText.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
        ) {
            Text(text = "Speech to text")
        }
        Button(
            onClick = { navController.navigate(Route.TextToSpeech.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
        ) {
            Text(text = "Text to speech")
        }
    }
}