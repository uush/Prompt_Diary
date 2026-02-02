package com.example.prompt_diary.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    // suspend: JS의 aysnc와 같다. DB 쓰기는 시간이 걸리니 비동기로 처리
    @Insert
    suspend fun insertDiary(diary: Diary)

    // Flow: 데이터가 변경되면 자동으로 화면에 알려주는 Stream
    // React의 실시간 구독과 비슷
    @Query("SELECT * FROM diary_table ORDER BY id DESC")
    fun getAllDiaries(): Flow<List<Diary>>

    // main에서 보여 줄 가장 최근 일기 1개 가져오는 함수
    @Query("SELECT * FROM diary_table ORDER BY id DESC LIMIT 1")
    fun getLatestDiary(): Flow<Diary?>
}