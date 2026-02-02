package com.example.prompt_diary.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.prompt_diary.data.AppDatabase
import com.example.prompt_diary.data.Diary
import kotlinx.coroutines.flow.Flow

// MainViewModel.kt
// AndroidViewModel 상속 (DB 접근 권한 획득)
class MainViewModel(application: Application): AndroidViewModel(application) {
    // 화면은 이 문자열을 구독만 하면 됩니다.
    private val _todayDate = mutableStateOf("")
    val todayDate: MutableState<String> = _todayDate
    private val db = AppDatabase.getDatabase(application)
    private val diaryDao = db.diaryDao()

    val latestDiary: Flow<Diary?> = diaryDao.getLatestDiary()

    init {
        fetchTodayDate()
    }

    private fun fetchTodayDate() {
        val now = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
        _todayDate.value = now.format(formatter)
    }
}