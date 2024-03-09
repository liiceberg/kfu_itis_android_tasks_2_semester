package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.util

import android.content.Context
import androidx.annotation.StringRes

class ResManager(private val ctx: Context) {
    fun getString(@StringRes res: Int): String = ctx.resources.getString(res)

    fun getString(@StringRes res: Int, vararg args: Any?): String {
        return ctx.resources.getString(res, *args)
    }
}