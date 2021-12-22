package com.bobobox.poketest.resources.util.network

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit


class TokenInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        builder.header("Accept", "application/json")
        val request = builder.build()
        return chain.proceed(
            try {
                request
            } catch (e: Exception) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(30, TimeUnit.DAYS)
                    .build()

                request.newBuilder()
                    .cacheControl(cacheControl)
                    .build()
            }
        )
    }
}