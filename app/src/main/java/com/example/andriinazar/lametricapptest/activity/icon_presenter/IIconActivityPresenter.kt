package com.example.andriinazar.lametricapptest.activity.icon_presenter

import com.example.andriinazar.lametricapptest.api.models.IconInfo

interface IIconActivityPresenter {
    fun onError(message: String)
    fun onInternetConnectionError()
    fun onDataUpdate(icons: List<IconInfo>)
    fun onShowProgress(showText: Boolean)
    fun onHideProgress()

}