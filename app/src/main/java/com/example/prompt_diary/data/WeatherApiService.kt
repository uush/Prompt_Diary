package com.example.prompt_diary.data

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    // baseUrl 뒤에 붙을 상세 주소
    @GET("v1/forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") currentWeather: Boolean = true
    ): WeatherResponse
}