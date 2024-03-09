package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.mapper.WeatherUiModelMapper
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.repository.WeatherRepository
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.model.WeatherUiModel

class GetWeatherDataUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: WeatherRepository,
    private val mapper: WeatherUiModelMapper
) {
    suspend operator fun invoke(city: String): WeatherUiModel {
        return withContext(dispatcher) {
            val weatherData = repository.getCurrentWeatherByCityName(city)
            mapper.mapDomainToUiModel(weatherData, city)
        }
    }

}