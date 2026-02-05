package com.example.prompt_diary.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prompt_diary.data.AppDatabase
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.prompt_diary.BuildConfig



class ChatViewModel (application: Application): AndroidViewModel(application) {
    // RoomDB 연결
    private val db = AppDatabase.getDatabase(application)
    private val diaryDao = db.diaryDao()

    // 화면에 보여줄 대답
    private val _chatResponse = MutableStateFlow("")
    val chatResponse = _chatResponse.asStateFlow()

    // Gemini Model 생성(API key)
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    fun sendMessage(userQuestion : String) {
        viewModelScope.launch {
            try {
                // 1. RoomDB에서 최신 일기를 읽는다.
                val latestDiary = diaryDao.getLatestDiaryOneShot()

                // 2. AI에게 전달할 프롬프트를 조립한다.
                val prompt = if (latestDiary != null) {
                    """
                    너는 내 소중한 친구이자 심리 상담가야.
                    아래는 내가 가장 최근에 쓴 일기 내용이야.
                    
                    [최근 일기]
                    날짜: ${latestDiary.date}
                    내용: ${latestDiary.content}
                    
                    이 일기 내용을 바탕으로, 사용자의 다음 질문에 따뜻하게 공감하며 대답해줘.
                    사용자 질문: "$userQuestion"
                    """
                } else {
                    // 일기가 없을 때
                    "사용자 질문: $userQuestion. (참고: 아직 작성된 일기가 없음)"
                }

                // 3. AI에게 메세지를 전달한다.
                val response = generativeModel.generateContent(prompt)
                _chatResponse.value = response.text ?: "대답을 생성하지 못했어요.😅"

            }catch (e: Exception) {
                _chatResponse.value = "🚫에러가 발생 했어요: ${e.localizedMessage}"
            }

        }
    }
}