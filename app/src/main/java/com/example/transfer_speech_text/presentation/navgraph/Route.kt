package com.example.transfer_speech_text.presentation.navgraph

sealed class Route(
    val route: String
) {
    data object HomeScreen: Route("HomeScreen")
    data object SpeechToText: Route("SpeechToText")
    data object TextToSpeech: Route("TextToSpeech")
}