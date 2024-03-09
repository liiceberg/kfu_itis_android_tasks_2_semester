package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.model

data class WeatherDomainModel(
    val additionalData: WeatherAdditionInfoDomainModel,
    val temperatureData: TemperatureDomainModel
)

fun WeatherDomainModel.isEmptyResponse(): Boolean {
    val isMainDataEmpty = with(temperatureData) {
        temperature == 0f && minTemp == 0f && feelsLike == 0f
    }
    val isWeatherDataEmpty = with(additionalData) {
        description.isEmpty() && icon.isEmpty()
    }
    return isMainDataEmpty && isWeatherDataEmpty
}
