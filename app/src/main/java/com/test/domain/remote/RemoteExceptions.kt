package com.test.domain.remote


sealed class RemoteExceptions(message: String?, cause: Throwable? = null) :
    Exception(message, cause) {
    data class NetworkConnection(
        override val message: String?,
        override val cause: Throwable? = null
    ) : RemoteExceptions(message, cause)

    data class BadRequest(override val message: String?, override val cause: Throwable? = null) :
        RemoteExceptions(message, cause)

    data class UnAuthorized(override val message: String?, override val cause: Throwable? = null) :
        RemoteExceptions(message, cause)

    data class InternalServerError(
        override val message: String?,
        override val cause: Throwable? = null
    ) : RemoteExceptions(message, cause)

    data class ResourceNotFound(
        override val message: String?,
        override val cause: Throwable? = null
    ) : RemoteExceptions(message, cause)

}