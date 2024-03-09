package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.model

data class WeatherUiModel(
    val temperatureData: TemperatureUiModel,
    val additionalData: WeatherAdditionInfoUiModel,
    val city: String
)

data class TemperatureUiModel(
    val temperature: Int,
    val minTemperature: Int,
    val feelsLike: Int,
)

data class WeatherAdditionInfoUiModel(
    val description: String,
    val icon: String
)


