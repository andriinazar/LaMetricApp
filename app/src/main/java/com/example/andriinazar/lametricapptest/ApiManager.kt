package com.example.andriinazar.lametricapptest

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiManager {


    fun authorizeUser() {
        ApiServiceBuilder.create(HeaderInterceptor())?.authorizeUser()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        { result -> responseCallback?.onResponseSuccess(result)},
                        { error -> responseCallback?.onResponseError(error) }
                )
    }
}