package com.example.andriinazar.lametricapptest

import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
                request()
                        .newBuilder()
                        .addHeader("client_id", BuildConfig.CLIENT_ID)
                        .addHeader("redirect_uri", "https://www.google.com")
                        .addHeader("response_type", "code")
                        .addHeader("Content-Type", "application/json")
                        .build()
        )
    }
}