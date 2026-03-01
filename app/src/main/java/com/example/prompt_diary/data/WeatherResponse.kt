package com.example.prompt_diary.data

import com.google.gson.annotations.SerializedName

// API에서 응답받을 전체 JSON 구조
data class WeatherResponse(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather
)

// 그 안의 실제 날씨 알맹이
data class CurrentWeather(
    val temperature: Double,
    val weathercode: Int // 0: 맑음, 1~3: 구름, 61: 비 등등 (나중에 아이콘으로 변환할 용도)
)