package com.example.transfer_speech_text.presentation.speech_to_text

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun SpeechToTextScreen(
    viewModel: SpeechToTextViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var canRecord by remember {
        mutableStateOf(false)
    }
    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            canRecord = isGranted
        }
    )
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            if (state.isSpeaking) {
                viewModel.stop()
            } else {
                viewModel.startRecord()
            }
        }
        ) {
            Text(text = "Press to record")
        }
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedContent(targetState = state.isSpeaking, label = "") { isSpeaking ->
            if (isSpeaking) {
                Text(text = "Speaking...")
            } else {
                Text(text = state.spokenText.ifEmpty { "Hold Click in mic to record" })
            }
        }
    }
}