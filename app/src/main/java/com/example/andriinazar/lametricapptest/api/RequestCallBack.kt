package com.example.andriinazar.lametricapptest.api

import com.example.andriinazar.lametricapptest.api.models.IconInfo
import com.example.andriinazar.lametricapptest.api.models.MetaData

interface RequestCallBack {
    fun onError(message: String?)
    fun onDataGet(icons: List<IconInfo>?)
    fun onMetadataGet(metaData: MetaData?)
    fun onResponseError(code: Int)
}