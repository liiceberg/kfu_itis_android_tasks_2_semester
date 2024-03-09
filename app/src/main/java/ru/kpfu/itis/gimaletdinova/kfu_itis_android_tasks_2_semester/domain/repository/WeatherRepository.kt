package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.repository

import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.model.WeatherDomainModel

interface WeatherRepository {
    suspend fun getCurrentWeatherByCityName(city: String): WeatherDomainModel
}