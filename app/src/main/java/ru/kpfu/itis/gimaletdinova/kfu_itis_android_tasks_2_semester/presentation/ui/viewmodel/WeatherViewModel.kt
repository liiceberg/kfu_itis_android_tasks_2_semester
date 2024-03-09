package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.ExceptionHandlerDelegate
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.model.WeatherUiModel
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.runCatching
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.di.ServiceLocator
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.domain.usecase.GetWeatherDataUseCase

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherDataUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
) : ViewModel() {

    private val _currentWeatherFlow = MutableStateFlow<WeatherUiModel?>(null)
    val currentWeatherFlow: StateFlow<WeatherUiModel?>
        get() = _currentWeatherFlow

    private val _loadingFlow = MutableStateFlow(false)
    val loadingFlow get() = _loadingFlow.asStateFlow()

    val errorsChannel = Channel<Throwable>()

    fun getWeatherInfo(city: String) {
        viewModelScope.launch {
            _loadingFlow.value = true
            runCatching(exceptionHandlerDelegate) {
                getWeatherUseCase.invoke(city)
            }.onSuccess { weatherModel ->
                _currentWeatherFlow.value = weatherModel
            }.onFailure { ex ->
                errorsChannel.send(ex)
            }
            _loadingFlow.value = false
        }
    }

    override fun onCleared() {
        errorsChannel.close()
        super.onCleared()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val useCase = ServiceLocator.getWeatherUseCase
                val exceptionHandlerDelegate = ServiceLocator.exceptionHandlerDelegate
                WeatherViewModel(
                    getWeatherUseCase = useCase,
                    exceptionHandlerDelegate = exceptionHandlerDelegate
                )
            }
        }
    }

}