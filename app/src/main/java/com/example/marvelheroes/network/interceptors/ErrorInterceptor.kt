package com.example.marvelheroes.network.interceptors

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor(val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        when(response.code()) {
            in 100..199 -> Log.d("ERROR_INTERCEPTOR", "${response.code()}: Information response")
            in 200..299 -> Log.d("ERROR_INTERCEPTOR", "${response.code()}: Successful response")
            in 300..399 -> Log.d("ERROR_INTERCEPTOR", "${response.code()}: Redirection message")
            in 400..499 -> Log.d("ERROR_INTERCEPTOR", "${response.code()}: Client error response")
            in 500..599 -> Log.d("ERROR_INTERCEPTOR", "${response.code()}: Server error response")
        }
        return response
    }
}