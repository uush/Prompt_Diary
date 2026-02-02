package com.example.prompt_diary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier

import com.example.prompt_diary.navigation.AppNavigation
import com.example.prompt_diary.ui.theme.Prompt_DiaryTheme

class MainActivity : ComponentActivity() {
    // 화면이 생성될 때, 딱 한 번 실행되는 생명주기 함수(ex useEffect)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 앱 전체의 테마를 설정합니다. (React의 ThemeProvider 역할)
            Prompt_DiaryTheme {
                // Surface는 배경색을 채워주는 기본 도화지입니다.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 여기에 화면 전환 엔진(라우터)을 넣습니다.
                    AppNavigation()
                }
            }
        }
    }
}





