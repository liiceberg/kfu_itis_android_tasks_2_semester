package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.pojo.response

import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("weather")
    val additionalData: List<AdditionalWeatherData>? = null,
    @SerializedName("main")
    val temperatureData: TemperatureData? = null,
)

class AdditionalWeatherData(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("icon")
    val icon: String? = null
)

class TemperatureData(
    @SerializedName("temp")
    val temperature: Float? = null,
    @SerializedName("feels_like")
    val feelsLike: Float? = null,
    @SerializedName("temp_min")
    val minTemp: Float? = null,
)
