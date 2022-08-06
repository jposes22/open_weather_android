package com.test.domain.repository

import com.test.domain.remote.RemoteExceptions
import retrofit2.HttpException
import java.net.SocketTimeoutException

open class BaseRepository {

    fun handleException(e: Exception): Throwable {
        return when (e) {
            // if httpException try to parse error
            is HttpException -> {
                when (e.code()) {
                    0 -> RemoteExceptions.NetworkConnection(e.message)
                    400 -> RemoteExceptions.BadRequest(e.message())
                    404 -> RemoteExceptions.ResourceNotFound(e.message())
                    401, 403 -> RemoteExceptions.UnAuthorized(e.message)
                    else -> RemoteExceptions.InternalServerError(e.message)
                }
            }
            is SocketTimeoutException -> RemoteExceptions.NetworkConnection(e.message)
            else -> RemoteExceptions.InternalServerError(e.message)
        }
    }
}