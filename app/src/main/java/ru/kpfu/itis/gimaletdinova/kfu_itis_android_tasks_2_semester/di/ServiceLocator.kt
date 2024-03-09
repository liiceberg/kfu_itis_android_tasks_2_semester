package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.di

import android.content.Context
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.BuildConfig
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.ExceptionHandlerDelegate
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.OpenWeatherApi
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.interceptor.AppIdInterceptor
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.interceptor.WeatherUnitsInterceptor
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.remote.mapper.WeatherDomainModelMapper
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.repository.WeatherRepositoryImpl
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.mapper.WeatherUiModelMapper
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.usecase.GetWeatherDataUseCase
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.util.ResManager

object ServiceLocator {
    private lateinit var weatherApi: OpenWeatherApi
    private lateinit var okHttpClient: OkHttpClient
    private val dispatcher = Dispatchers.IO
    lateinit var exceptionHandlerDelegate: ExceptionHandlerDelegate
        private set
    lateinit var getWeatherUseCase: GetWeatherDataUseCase
        private set

    private lateinit var weatherRepository: WeatherRepositoryImpl
    private val weatherDomainModelMapper = WeatherDomainModelMapper()
    private val weatherUiModelMapper = WeatherUiModelMapper()

    fun initDataDependencies(ctx: Context) {
        initOkHttpClient()
        initWeatherApi()

        weatherRepository = WeatherRepositoryImpl(
            api = weatherApi,
            weatherDomainModelMapper = weatherDomainModelMapper,
            resManager = ResManager(ctx),
        )

        exceptionHandlerDelegate = ExceptionHandlerDelegate(resManager = ResManager(ctx))
    }

    fun initDomainDependencies() {
        getWeatherUseCase = GetWeatherDataUseCase(
            dispatcher = dispatcher,
            repository = weatherRepository,
            mapper = weatherUiModelMapper
        )
    }

    private fun initOkHttpClient() {
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(AppIdInterceptor())
            .addInterceptor(WeatherUnitsInterceptor())

        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        okHttpClient = clientBuilder.build()
    }

    private fun initWeatherApi() {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.OPEN_WEATHER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherApi = builder.create(OpenWeatherApi::class.java)
    }
}