package com.example.prompt_diary.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.prompt_diary.ui.viewmodel.DiaryViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryListScreen(onBack: () -> Unit, diaryViewModel : DiaryViewModel): Unit {

    Scaffold(
        topBar = { TopAppBar(title = { Text("My Diaries") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "뒤로 가기")
                }
            }) }
    )
    { padding ->
        Column {
            LazyColumn(modifier = Modifier.padding(padding)) { }
        }
    }
}