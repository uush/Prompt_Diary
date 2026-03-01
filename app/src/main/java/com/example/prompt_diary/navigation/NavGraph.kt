package com.example.prompt_diary.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prompt_diary.ui.screens.MainScreen
import com.example.prompt_diary.ui.screens.ChatScreen
import com.example.prompt_diary.ui.screens.DiaryScreen
import com.example.prompt_diary.ui.viewmodel.ChatViewModel
import com.example.prompt_diary.ui.viewmodel.MainViewModel
import com.example.prompt_diary.ui.viewmodel.DiaryViewModel
import com.example.prompt_diary.ui.viewmodel.WeatherViewModel


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // viewModel() 함수는 이미 메모리에 만들어진 게 있으면 그걸 가져오고, 없으면 새로 만듭니다.
    val mainViewModel: MainViewModel = viewModel()
    val diaryViewModel: DiaryViewModel = viewModel()
    val chatViewModel : ChatViewModel = viewModel()
    val weatherViewModel : WeatherViewModel = viewModel()


    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onWriteDiary = { navController.navigate("diary") },
                onOpenChat = { navController.navigate("chat") },
                dateViewModel = mainViewModel,
                weatherViewModel = weatherViewModel
            )
        }
        composable("chat") {
            ChatScreen(
                onBack = { navController.popBackStack() },
                viewModel = chatViewModel
            )
        }

        composable("diary") {
            DiaryScreen(
                onBack = { navController.popBackStack() },
                viewModel = diaryViewModel
                )
        }
    }
}