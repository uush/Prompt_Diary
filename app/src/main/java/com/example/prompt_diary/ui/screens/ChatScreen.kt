package com.example.prompt_diary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.prompt_diary.ui.viewmodel.ChatViewModel
import androidx.compose.ui.graphics.Color

@Composable
fun ChatScreen(onBack: () -> Unit, viewModel: ChatViewModel) {
    var inputText by remember { mutableStateOf("") }
    val aiResponse by viewModel.chatResponse.collectAsState()
    val scrollState = rememberScrollState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.Start).padding(top = 24.dp)
        ) {
            Text(text="뒤로 가기")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // AI 답변 텍스트 뷰
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .heightIn(min = 100.dp, max = 400.dp)
                .verticalScroll(rememberScrollState()),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

        ) {
            SelectionContainer {
            if (aiResponse.isNotEmpty()) {
                Text(
                    text = aiResponse,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Text(
                    text = "안녕하세요!\n일기 내용을 바탕으로 대화할 수 있어요. 무엇이든 물어보세요! 😊",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
            }


        }

        // 입력창과 버튼
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("AI와 대화해 보세요💬") },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                // 간단한 AI 모방 응답 (실제로는 API 호출)
                viewModel.sendMessage(inputText)
                inputText = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text("전송하기")
            }
        }


    }
}