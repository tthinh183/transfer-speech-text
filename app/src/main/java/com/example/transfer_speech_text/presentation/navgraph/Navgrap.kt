package com.example.transfer_speech_text.presentation.navgraph


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.transfer_speech_text.presentation.home.HomeScreen
import com.example.transfer_speech_text.presentation.speech_to_text.SpeechToTextScreen
import com.example.transfer_speech_text.presentation.speech_to_text.SpeechToTextViewModel
import com.example.transfer_speech_text.presentation.text_to_speech.TextToSpeechScreen

@Composable
fun NavGraph(
    destination: String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = destination
    ) {
        composable(
            route = Route.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Route.SpeechToText.route
        ) {
            SpeechToTextScreen()
        }
        composable(
            route = Route.TextToSpeech.route
        ) {
            TextToSpeechScreen()
        }
    }
}