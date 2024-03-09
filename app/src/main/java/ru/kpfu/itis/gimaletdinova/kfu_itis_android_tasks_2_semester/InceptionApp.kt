package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester

import android.app.Application
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.di.ServiceLocator

class InceptionApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceLocator.initDataDependencies(this)
        ServiceLocator.initDomainDependencies()
    }
}