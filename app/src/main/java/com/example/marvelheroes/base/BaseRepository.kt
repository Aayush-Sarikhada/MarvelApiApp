package com.example.marvelheroes.base

import com.google.gson.Gson
import retrofit2.Response

abstract class BaseRepository {
    fun <T> handleResponse(response: Response<T>): Result<T> {
        return try {
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.success(body)
                }
                return Result.failure(Throwable("No Data Found!"))
            } else {
                if (response.code() in 400..499) {
                    response.errorBody().let {
                        return Result.failure(Throwable("Error code ${response.code()}: ${response.message()}"))
                    }
                } else {
                    return Result.failure(Throwable(response.message()))
                }
            }
        } catch (e: Error) {
            Result.failure(Throwable(e.message ?: "Something went wrong!"))
        }
    }
}