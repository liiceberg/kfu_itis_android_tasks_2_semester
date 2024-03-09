package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.mapper

import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.model.WeatherDomainModel
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.model.TemperatureUiModel
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.model.WeatherAdditionInfoUiModel
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.model.WeatherUiModel

class WeatherUiModelMapper {
    fun mapDomainToUiModel(input: WeatherDomainModel, city: String): WeatherUiModel {
        with(input) {
            return WeatherUiModel(
                temperatureData = TemperatureUiModel(
                    temperature = temperatureData.temperature.toInt(),
                    minTemperature = temperatureData.minTemp.toInt(),
                    feelsLike = temperatureData.feelsLike.toInt(),
                ),
                additionalData = WeatherAdditionInfoUiModel(
                    description = additionalData.description,
                    icon = additionalData.icon
                ),
                city = city
            )
        }
    }
}