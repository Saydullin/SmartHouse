package com.saydullin.smarthouse.presentation.model

data class ErrorInfo(
    val id: Int,
    val message: String,
    val isHidden: Boolean
) {

    companion object {
        val empty = ErrorInfo(
            id = 1,
            message = "",
            isHidden = true
        )

        fun setError(message: String): ErrorInfo {
            return ErrorInfo(
                id = 1,
                message = message,
                isHidden = false
            )
        }
    }

}