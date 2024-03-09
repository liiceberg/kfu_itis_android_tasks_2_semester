package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.base.Keys

class WeatherUnitsInterceptor : Interceptor {

    private val unitsValue = "metric"
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url.newBuilder()
            .addQueryParameter(Keys.UNITS_KEY, unitsValue)
            .build()

        val requestBuilder = chain.request().newBuilder().url(newUrl)

        return chain.proceed(requestBuilder.build())
    }
}