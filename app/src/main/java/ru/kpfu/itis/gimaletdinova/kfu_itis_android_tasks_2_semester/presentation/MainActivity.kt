package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.R
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.presentation.ui.fragment.WeatherFragment

class MainActivity : AppCompatActivity() {

    val fragmentContainerId: Int = R.id.main_container
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(
                fragmentContainerId,
                WeatherFragment(),
                WeatherFragment.HOME_FRAGMENT_TAG,
            )
            .commit()
    }
}