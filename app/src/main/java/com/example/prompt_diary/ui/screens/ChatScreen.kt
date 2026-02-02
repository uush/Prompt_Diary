package com.example.prompt_diary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(onBack: () -> Unit) {
    var inputText by remember { mutableStateOf("") }
    var aiResponse by remember { mutableStateOf("여기에 AI 답변이 나타납니다.") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(text="뒤로 가기")
        }

        // AI 답변 텍스트 뷰
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = aiResponse,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // 입력창과 버튼
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("질문을 입력하세요") },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                // 간단한 AI 모방 응답 (실제로는 API 호출)
                aiResponse = when {
                    inputText.contains("Hi") -> "안녕하세요! 반갑습니다. 😊"
                    inputText.contains("climate") -> "오늘 서울 날씨는 맑음입니다!"
                    inputText.contains("Kotlin") -> "Kotlin Compose 배우시는군요! 좋은 선택입니다."
                    inputText.isNotBlank() -> "입력하신 '${inputText}'에 대한 답변입니다."
                    else -> "질문을 입력해주세요!"
                }
                inputText = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("AI에게 질문하기")
        }
    }
}