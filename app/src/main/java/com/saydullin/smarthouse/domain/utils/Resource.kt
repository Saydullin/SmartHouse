package com.saydullin.smarthouse.domain.utils

enum class StatusCode {
    NO_ERROR,
    SERVER_ERROR,
    CONNECTION_ERROR,
    UPDATE_ERROR,
    DATABASE_ERROR,
    DATABASE_NOT_FOUND_ERROR,
    SYSTEM_ERROR,
    DATA_ERROR,
    UNKNOWN_ERROR,

    // Auth error codes
    ERROR_USER_DISABLED,
    ERROR_USER_NOT_FOUND,
    ERROR_USER_TOKEN_EXPIRED,
    ERROR_INVALID_USER_TOKEN,
    ERROR_INVALID_CREDENTIAL,
    ERROR_INVALID_EMAIL,
    ERROR_WEAK_PASSWORD,

    USER_NOT_AUTHENTICATED,
}

sealed class Resource<T>(
    val data: T? = null,
    val statusCode: StatusCode = StatusCode.NO_ERROR,
    val message: String? = null
) {

    class Success<T>(data: T?): Resource<T>(data)

    class Error<T>(
        statusCode: StatusCode = StatusCode.UNKNOWN_ERROR,
        message: String? = "",
        data: T? = null
    ): Resource<T>(data, statusCode, message)

}


