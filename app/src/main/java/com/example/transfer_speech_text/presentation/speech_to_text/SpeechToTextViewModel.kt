package com.example.transfer_speech_text.presentation.speech_to_text

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.LOGGER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SpeechToTextViewModel @Inject constructor(
    private val speechRecognizer: SpeechRecognizer
) : ViewModel(), RecognitionListener {

    init {
        speechRecognizer.setRecognitionListener(this)
    }

    private val _state = MutableStateFlow(VoiceToTextParserState())
    val state = _state.asStateFlow()

    companion object {
        const val RESULT_SPEECH = 1
    }

    fun startRecord() {
        _state.update {
            VoiceToTextParserState()
        }
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        }
        speechRecognizer.startListening(intent)
        _state.update {
            it.copy(
                isSpeaking = true
            )
        }
    }

    fun stop() {
        _state.update {
            it.copy(
                isSpeaking = false
            )
        }
        speechRecognizer.stopListening()
    }

    override fun onReadyForSpeech(params: Bundle?) {
        _state.update {
            it.copy(
                error = null
            )
        }
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(rmsdB: Float) = Unit

    override fun onBufferReceived(buffer: ByteArray?) = Unit

    override fun onEndOfSpeech() {
        _state.update {
            it.copy(
                isSpeaking = false
            )
        }
    }

    override fun onError(error: Int) {
        if (error == SpeechRecognizer.ERROR_CLIENT) {
            return
        } else {
            _state.update {
                it.copy(
                    error = "Error: $error"
                )
            }
        }
    }

    override fun onResults(results: Bundle?) {
        results
            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0)
            ?.let { result ->
                _state.update {
                    it.copy(
                        spokenText = result
                    )
                }
            }
    }

    override fun onPartialResults(partialResults: Bundle?) = Unit

    override fun onEvent(eventType: Int, params: Bundle?) = Unit
}
data class VoiceToTextParserState(
    val spokenText: String = "",
    val isSpeaking: Boolean = false,
    val error: String? = null
)