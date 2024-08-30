package com.vladzah.remote.interceptors

import okhttp3.Interceptor

val cacheInterceptor = Interceptor { chain ->
    var request = chain.request()
    request = request.newBuilder()
        .header("Cache-Control", "public, max-age=" + 60)
        .build()
    chain.proceed(request)
}