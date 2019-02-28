package com.example.andriinazar.lametricapptest

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiManager {


    companion object {
        fun getIcons(pages_count: Int, pages_size: Int, order: String, callBack: RequestCallBack) {
            ApiServiceBuilder.create(HeaderInterceptor())?.getIcons(pages_count, pages_size, order)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                            { result ->
                                if (result.code() == 200) {
                                    callBack.onDataGet(result.body()?.data)
                                } else {
                                    callBack.onResponseError(result.code())
                                }
                            },
                            { error ->
                                callBack.onError(error.message)
                            }
                    )
        }
    }

}