package com.example.transfer_speech_text.di

import android.app.Application
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSpeechRecognizer(
        application: Application
    ): SpeechRecognizer {
        return SpeechRecognizer.createSpeechRecognizer(application)
    }

}