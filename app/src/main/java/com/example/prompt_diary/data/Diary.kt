package com.example.prompt_diary.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_table")
data class Diary(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // id는 1씩 자동 증가
    val date: String, // 날짜
    val content: String, // 일기 내용
)