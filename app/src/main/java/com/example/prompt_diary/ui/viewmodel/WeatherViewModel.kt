package com.example.prompt_diary.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prompt_diary.data.RetrofitClient
import kotlinx.coroutines.launch



class WeatherViewModel (application: Application) : AndroidViewModel(application) {
    private val _weatherState = mutableStateOf("날씨를 불러오는 중...")
    val weatherState: State<String> = _weatherState

    // 초기화 : viewModel 생성시, 날씨 정보 호출
    init {
        getWeather(37.566, 126.9784)
    }

    // 날씨 정보 가져오기(비동기)
    private fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                // RetrofitClient를 통해 API 호출
                val response = RetrofitClient.weatherApi.getCurrentWeather(lat, lon)
                val temp = response.currentWeather.temperature
                val weatherCode = response.currentWeather.weathercode

                // 날씨 코드를 이모지로 변환
                val emoji = when (weatherCode){
                    0 -> "☀️"          // 맑음
                    in 1..3 -> "☁️"    // 구름 조금, 흐림
                    in 45..48 -> "🌫️"  // 안개
                    in 51..57 -> "🌦️"  // 이슬비
                    in 61..67 -> "🌧️"  // 비
                    in 71..77 -> "❄️"  // 눈
                    in 80..82 -> "☔"  // 소나기
                    in 85..86 -> "☃️"  // 폭설
                    in 95..99 -> "⚡"  // 번개
                    else -> "🌤️"       // 기타
                }
                _weatherState.value = "$emoji $temp°C"
            }catch (e : Exception){
                _weatherState.value = "날씨 정보 없음"
                e.printStackTrace()
            }
        }
    }
}