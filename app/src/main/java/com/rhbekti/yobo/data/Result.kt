package com.rhbekti.yobo.data

sealed class Result<out T: Any?> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
