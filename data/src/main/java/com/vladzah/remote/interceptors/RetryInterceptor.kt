package com.vladzah.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

val retryInterceptor = Interceptor { chain ->
    var request = chain.request()
    var response: Response = chain.proceed(request)
    var tryCount = 0
    val maxLimit = 3

    while (!response.isSuccessful && tryCount < maxLimit) {
        tryCount++
        response = chain.proceed(request)
    }
    response
}