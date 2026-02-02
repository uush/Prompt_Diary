package com.example.prompt_diary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.prompt_diary.ui.viewmodel.MainViewModel


// 메인 화면 : 날씨 + 어제 일기
@Composable
fun MainScreen(onWriteDiary: () -> Unit, onOpenChat: () -> Unit, viewModel: MainViewModel) {
    // Flow를 Compose 상태로 전환
    // 화면이 켜지면 DB에서 최신 일기를 실시간으로 감시
    val latestDiaryState = viewModel.latestDiary.collectAsState(initial = null)
    // 데이터 꺼내기
    val diary = latestDiaryState.value

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                FloatingActionButton(
                    onClick = onWriteDiary,
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "일기 쓰기")
                }
                FloatingActionButton(
                    onClick = onOpenChat,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = "AI 챗봇")
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(24.dp)) {
            Text(text = viewModel.todayDate.value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("오늘의 날씨: ☀️ 맑음", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(32.dp))
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(300.dp)){
                    Text(
                        "최근 일기",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    if (diary != null) {
                        Column(modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f) // 남은 공간을 꽉 채움 (스크롤 영역 확보)
                                .verticalScroll(rememberScrollState()) // 2. 스크롤 기능 추가!
                        ) {
                            // 날짜
                            Text(
                                text = diary.date,
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.Gray
                            )
                            // 일기 내용
                            Text(
                                text = diary.content,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.DarkGray
                            )
                        }
                    } else {
                        Text(
                            text = "최근에 작성된 일기가 없어요.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )
                    }
                    }
                }
        }
    }
}