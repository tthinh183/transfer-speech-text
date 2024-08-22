package com.example.transfer_speech_text.presentation.text_to_speech

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import com.example.transfer_speech_text.presentation.speech_to_text.VoiceToTextParserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TextToSpeechViewModel @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(TextToSpeechState())
    val state = _state.asStateFlow()

    private  var  textToSpeech: TextToSpeech? = null

    fun textToSpeech(context: Context) {
        _state.update {
            it.copy(
                isButtonEnabled = false
            )
        }
        textToSpeech = TextToSpeech(
             context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech.let {
                    text ->
                    text?.language = Locale.US
                    text?.setSpeechRate(1.0f)
                    text?.speak(
                        _state.value.text,
                        TextToSpeech.QUEUE_ADD,
                        null, null
                    )
                }
            }
            _state.update {
                it.copy(
                    isButtonEnabled = true
                )
            }
        }
    }

    fun onTextFieldValueChange(query: String) {
        _state.value = state.value.copy(
            text = query
        )
    }
}

data class TextToSpeechState(
    val isButtonEnabled:Boolean = true,
    val text:String = ""
)