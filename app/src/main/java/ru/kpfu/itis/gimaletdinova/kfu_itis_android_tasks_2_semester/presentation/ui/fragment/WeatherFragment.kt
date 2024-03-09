package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.BuildConfig
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.R
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.databinding.FragmentWeatherBinding
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.MainActivity
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.ui.viewmodel.WeatherViewModel
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.util.observe

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val binding: FragmentWeatherBinding by viewBinding(FragmentWeatherBinding::bind)

    private val weatherViewModel: WeatherViewModel by viewModels {
        WeatherViewModel.Factory
    }

    private var counter = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observerData()
        with(binding) {
            if (BuildConfig.DEBUG) {
                headerTv.setOnClickListener {
                    debugCall()
                }
            }
            searchBtn.setOnClickListener {
                loadWeatherInfo()
            }

            userInputEt.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    loadWeatherInfo()
                }
                true
            }
        }
    }

    private fun observerData() {

        with(weatherViewModel) {

            currentWeatherFlow.observe(this@WeatherFragment) { weatherData ->
                weatherData?.apply {

                    binding.run {
                        cityTv.text = city.uppercase()
                        temperatureTv.text =
                            getString(R.string.temperature, temperatureData.temperature)
                        feelsTemperatureTv.text = getString(
                            R.string.feels_temperature,
                            temperatureData.feelsLike
                        )
                        minTemperatureTv.text =
                            getString(
                                R.string.min_temperature,
                                temperatureData.minTemperature
                            )

                        descriptionTv.text = additionalData.description

                        Glide.with(requireContext())
                            .load(getString(R.string.icon_url, additionalData.icon))
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder)
                            .into(imageIv)
                    }
                }
            }

            loadingFlow.observe(this@WeatherFragment) { isLoad ->
                binding.progressBar.apply {
                    visibility = if (isLoad) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }

            lifecycleScope.launch {
                errorsChannel.consumeEach { ex ->
                    Toast.makeText(
                        context,
                        ex.message ?: getString(R.string.unknown_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun loadWeatherInfo() {
        with(binding) {
            if (isInputCorrect(userInputEt)) {
                val city = userInputEt.text.toString()
                weatherViewModel.getWeatherInfo(city)
            }
        }
    }
    private fun isInputCorrect(userInputEt: EditText): Boolean {
        if (userInputEt.text.trim().isEmpty()) {
            userInputEt.error = getString(R.string.empty_city_input_error)
            return false
        }
        if (userInputEt.text.matches(Regex("[A-Za-z]+")).not()) {
            userInputEt.error = getString(R.string.incorrect_city_input_error)
            return false
        }
        return true
    }

    private fun debugCall() {
        counter++
        lifecycleScope.launch {
            delay()
        }
        val clickCount = 4
        if (counter == clickCount) {
            parentFragmentManager.beginTransaction()
                .replace(
                    (requireActivity() as MainActivity).fragmentContainerId,
                    DebugFragment(),
                    DebugFragment.DEBUG_FRAGMENT_TAG
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private suspend fun delay() {
        delay(1000L)
        counter--
    }


    companion object {
        const val HOME_FRAGMENT_TAG = "HOME_FRAGMENT_TAG"
    }
}
