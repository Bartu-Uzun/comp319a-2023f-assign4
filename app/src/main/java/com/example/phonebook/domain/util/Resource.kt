package com.example.phonebook.domain.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Succes<T>(data: T): Resource<T>(data = data)
    class Error<T>(message: String): Resource<T>(message = message)
    class Loading<T>(data: T? = null): Resource<T>(data)
}
