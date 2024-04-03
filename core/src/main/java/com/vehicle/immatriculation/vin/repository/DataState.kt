package com.vehicle.immatriculation.vin.repository


sealed class DataState<T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Failure<T>(val errorHandler: ErrorHolder) : DataState<T>()

    data class Processing<T>(val msg: String) : DataState<T>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> failure(errorHandler: ErrorHolder) = Failure<T>(errorHandler)
    }

    inline fun onSuccess(block: (T) -> Unit): DataState<T> = apply {
        if (this is Success) {
            block(data)
        }
    }

    inline fun onFailure(block: (ErrorHolder) -> Unit): DataState<T> = apply {
        if (this is Failure) {
            block(errorHandler)
        }
    }
}

