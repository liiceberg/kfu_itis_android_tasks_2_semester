package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.repository

import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.R
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.exception.EmptyWeatherResponseException
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.OpenWeatherApi
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.mapper.WeatherDomainModelMapper
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.model.WeatherDomainModel
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.model.isEmptyResponse
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.repository.WeatherRepository
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.util.ResManager

class WeatherRepositoryImpl(
    private val api: OpenWeatherApi,
    private val resManager: ResManager,
    private val weatherDomainModelMapper: WeatherDomainModelMapper
) : WeatherRepository {
    override suspend fun getCurrentWeatherByCityName(city: String): WeatherDomainModel {
        val domainModel = weatherDomainModelMapper.mapResponseToDomainModel(
            input = api.getCurrentWeatherByCity(city = city)
        )
        return if (domainModel != null && domainModel.isEmptyResponse().not()) {
            domainModel
        } else {
            throw EmptyWeatherResponseException(resManager.getString(R.string.empty_weather_response))
        }
    }
}