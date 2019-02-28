package com.example.andriinazar.lametricapptest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.disposables.Disposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiServiceBuilder {

    companion object {

        // default request settings
        fun create(authHeader: Interceptor): ApiService? {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(getDefaultGsonConverter()))
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(getDefaultClient(authHeader))
                    .build()

            return retrofit.create(ApiService::class.java)
        }

        private fun getDefaultGsonConverter() : Gson {
            return GsonBuilder()
                    .setLenient()
                    .create()
        }

        private fun getDefaultClient(authHeader: Interceptor) : OkHttpClient {
            return OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(authHeader)
                    .build()
        }

        var disposable: Disposable? = null
    }
}