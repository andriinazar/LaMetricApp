package com.example.andriinazar.lametricapptest

interface RequestCallBack {
    fun onError(message: String?)
    fun onDataGet(icons: List<IconInfo>?)
    fun onResponseError(code: Int)
}