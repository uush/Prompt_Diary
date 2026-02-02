package com.example.prompt_diary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Diary::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao

    companion object {
        //Singleton pattern : DB 인터페이스는 앱 전체에서 딱 하나만 있어야 메모리가 안 터진다.
        @Volatile // DB를 만들자마자 모든 일꾼에게(멀티 쓰레드) 즉시 생중계(캐시 무시하고 메모리 직접 읽기)
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // 1. INSTANCE가 null이 아니면 -> 바로 리턴 (?: 뒤를 실행 안 함)
            // 2. null이면 -> synchronized 블록 실행
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // 폰에 저장될 실제 파일 이름 (app_database.db)
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}