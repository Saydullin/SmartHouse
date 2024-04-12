package com.saydullin.smarthouse.presentation.utils

import android.content.Context
import com.saydullin.smarthouse.R
import com.saydullin.smarthouse.domain.utils.StatusCode

class ErrorMessage {

    companion object {

        fun execute(context: Context, e: StatusCode): String {
            return when(e) {
                StatusCode.SERVER_ERROR -> {
                    context.getString(R.string.error_server_error)
                }
                StatusCode.CONNECTION_ERROR -> {
                    context.getString(R.string.error_connection_error)
                }
                StatusCode.ERROR_USER_DISABLED -> {
                    context.getString(R.string.error_user_disabled)
                }
                StatusCode.ERROR_USER_TOKEN_EXPIRED -> {
                    context.getString(R.string.error_user_token_expired)
                }
                StatusCode.ERROR_INVALID_USER_TOKEN -> {
                    context.getString(R.string.error_invalid_user_token)
                }
                StatusCode.ERROR_USER_NOT_FOUND -> {
                    context.getString(R.string.error_user_not_found)
                }
                StatusCode.ERROR_INVALID_CREDENTIAL -> {
                    context.getString(R.string.error_invalid_credential)
                }
                StatusCode.ERROR_INVALID_EMAIL -> {
                    context.getString(R.string.error_invalid_email)
                }
                StatusCode.ERROR_WEAK_PASSWORD -> {
                    context.getString(R.string.error_weak_password)
                }
                StatusCode.USER_NOT_AUTHENTICATED -> {
                    context.getString(R.string.error_weak_password)
                }
                else -> {
                    context.getString(R.string.error_unknown_error)
                }
            }
        }

    }

}