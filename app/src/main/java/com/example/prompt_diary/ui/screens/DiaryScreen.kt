package com.example.prompt_diary.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.prompt_diary.ui.viewmodel.DiaryViewModel



@Composable
fun DiaryScreen(onBack: () -> Unit, viewModel: DiaryViewModel): Unit {
    var text by remember { mutableStateOf("") }
    var enable by remember { mutableStateOf( false) }
    var context = LocalContext.current


    Column (Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ){
        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(text="뒤로 가기")
        }
        // 일기 작성 박스
     OutlinedTextField(
         value = text,
         onValueChange = {
             text = it
             enable = true
                         },
         modifier = Modifier
             .fillMaxWidth()
             .height(400.dp)
             .padding(horizontal = 10.dp),
         label = {Text("오늘의 일기")},
         placeholder = {Text("오늘은 어떤 일이 있었나요?")},
         maxLines = 10,
     )

        Button(
            enabled = text.isNotBlank() || enable,
            onClick = {
                if (!enable) return@Button
                viewModel.saveDiary(text)
                Toast.makeText(context, "저장 완료!!", Toast.LENGTH_LONG).show()
                text = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

        )
        { (Text(text="저장하기"))}
    }
}