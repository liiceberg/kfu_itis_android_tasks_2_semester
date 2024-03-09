package ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data

import retrofit2.HttpException
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.R
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.exception.BadRequestException
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.exception.PageNotFoundException
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.exception.ServerException
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.exception.TooManyRequestsException
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.data.exception.UserNotAuthorizedException
import ru.kpfu.itis.gimaletdinova.kfu_itis_android_tasks_2_semester.util.ResManager

class ExceptionHandlerDelegate(
    private val resManager: ResManager
) {
    fun handleException(ex: Throwable): Throwable {
        return when (ex) {
            is HttpException -> {
                when (ex.code()) {

                    400 -> BadRequestException(message = resManager.getString(R.string.bad_request))
                    401 -> UserNotAuthorizedException(message = resManager.getString(R.string.user_not_authorized))
                    404 -> PageNotFoundException(message = resManager.getString(R.string.page_not_found))
                    429 -> TooManyRequestsException(message = resManager.getString(R.string.too_many_requests))
                    in 500..599 -> ServerException(message = resManager.getString(R.string.server_exception))
                    else -> ex
                }
            }
            else -> ex
        }
    }
}