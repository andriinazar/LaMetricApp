package com.example.andriinazar.lametricapptest.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiManager {



    companion object {
        fun getIcons(pages_count: Int, pages_size: Int, order: String, callBack: RequestCallBack) {
            ApiServiceBuilder.disposable = ApiServiceBuilder.create()?.getIcons(pages_count, pages_size, order)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                            { result ->
                                if (result.code() == 200) {
                                    callBack.onDataGet(result.body()?.data)
                                    callBack.onMetadataGet(result.body()?.meta)
                                } else {
                                    callBack.onResponseError(result.code())
                                }
                            },
                            { error ->
                                callBack.onError(error.message)
                            }
                    )
        }

        fun hasNetwork(context: Context): Boolean {
            var isConnected: Boolean = false // Initial Value
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }

        fun disposeRequest() {
            ApiServiceBuilder.disposable?.dispose()
        }
    }



}