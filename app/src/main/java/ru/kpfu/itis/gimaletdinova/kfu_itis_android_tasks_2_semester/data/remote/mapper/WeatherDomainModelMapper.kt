package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.mapper

import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.pojo.response.WeatherResponse
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.model.TemperatureDomainModel
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.model.WeatherAdditionInfoDomainModel
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.model.WeatherDomainModel

class WeatherDomainModelMapper {
    fun mapResponseToDomainModel(input: WeatherResponse?): WeatherDomainModel? {
        return input?.let { response ->
            WeatherDomainModel(
                temperatureData = TemperatureDomainModel(
                    temperature = response.temperatureData?.temperature ?: 0f,
                    feelsLike = response.temperatureData?.feelsLike ?: 0f,
                    minTemp = response.temperatureData?.minTemp ?: 0f
                ),
                additionalData = WeatherAdditionInfoDomainModel(
                    description = response.additionalData?.first()?.description ?: "",
                    icon = response.additionalData?.first()?.icon ?: ""
                )
            )
        }
    }
}