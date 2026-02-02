package com.example.prompt_diary.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.prompt_diary.data.AppDatabase
import com.example.prompt_diary.data.Diary
import kotlinx.coroutines.launch
import java.time.LocalDate

// ViewMoel이 DB를 쓰려면 Context(Application)가 필요해서 androidViewModel을 상속받는다.
class DiaryViewModel (application: Application) : AndroidViewModel(application){

    // 1. DB랑 연결된 DAO를 가져온다.
    private val db = AppDatabase.getDatabase(application)
    private val diaryDao = db.diaryDao()

    // 2. 저장 함수
    fun saveDiary(content: String){
        if(content.isBlank()) return

        // viewModelScope.launch: 코루틴(비동기) 실행
        viewModelScope.launch {
            val today = LocalDate.now().toString() // 오늘 날짜
            val newDiary = Diary(content = content, date = today)

            diaryDao.insertDiary(newDiary) // 저장 완료
        }
    }
}