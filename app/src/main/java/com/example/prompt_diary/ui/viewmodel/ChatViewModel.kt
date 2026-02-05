package com.example.prompt_diary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.prompt_diary.BuildConfig


class ChatViewModel : ViewModel() {
    // 화면에 보여줄 대답
    private val _chatResponse = MutableStateFlow("")
    val chatResponse = _chatResponse.asStateFlow()

    // Gemini Model 생성(API key)
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    fun sendMessage(userMessage : String) {
        viewModelScope.launch {
            try {
                val response = generativeModel.generateContent(userMessage)

                _chatResponse.value = response.text ?: "대답을 생성하지 못했어요.😅"
            }catch (e: Exception) {
                _chatResponse.value = "🚫에러가 발생 했어요: ${e.localizedMessage}"
            }

        }
    }
}