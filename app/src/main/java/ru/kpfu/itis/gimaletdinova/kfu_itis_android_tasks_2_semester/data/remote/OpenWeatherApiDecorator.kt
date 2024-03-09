package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote

import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.ExceptionHandlerDelegate
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.pojo.response.WeatherResponse
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.runCatching

class OpenWeatherApiDecorator(
    private val openWeatherApi: OpenWeatherApi,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
) {
    suspend fun getCurrentWeatherByCity(city: String): Result<WeatherResponse?> {
        return runCatching(exceptionHandlerDelegate) {
            openWeatherApi.getCurrentWeatherByCity(city)
        }
    }
}