package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.pojo.response.WeatherResponse

interface OpenWeatherApi {
    @GET("weather")
    suspend fun getCurrentWeatherByCity(
        @Query(value = "q") city: String,
    ): WeatherResponse?
}