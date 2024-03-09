package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.BuildConfig
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.R
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.databinding.FragmentDebugBinding

class DebugFragment: Fragment(R.layout.fragment_debug) {

    private val binding: FragmentDebugBinding by viewBinding(FragmentDebugBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            applicationNameTv.text = getString(R.string.application_name, getString(R.string.app_name))
            baseUrlTv.text = getString(R.string.base_url, BuildConfig.OPEN_WEATHER_BASE_URL)
            versionNameTv.text = getString(R.string.version_name, BuildConfig.VERSION_NAME)
            versionCodeTv.text = getString(R.string.version_code, BuildConfig.VERSION_CODE)
            manufacturerTv.text = getString(R.string.manufacturer, Build.MANUFACTURER)
            modelTv.text = getString(R.string.model, Build.MODEL)
            androidDigitalVersionTv.text = getString(R.string.android_digital_version, Build.VERSION.RELEASE)
            apiVersion.text = getString(R.string.api_version, Build.VERSION.SDK_INT)
        }
    }

    companion object {
        const val DEBUG_FRAGMENT_TAG = "DEBUG_FRAGMENT_TAG"
    }

}