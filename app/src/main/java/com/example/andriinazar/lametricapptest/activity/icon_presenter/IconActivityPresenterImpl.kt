package com.example.andriinazar.lametricapptest.activity.icon_presenter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.andriinazar.lametricapptest.api.ApiManager
import com.example.andriinazar.lametricapptest.api.RequestCallBack
import com.example.andriinazar.lametricapptest.api.RequestConstants
import com.example.andriinazar.lametricapptest.api.models.IconInfo
import com.example.andriinazar.lametricapptest.api.models.MetaData

class IconActivityPresenterImpl(var context: Context, var presenter: IIconActivityPresenter?) {

    var currentDownloadPage = 0
    var maxPages = 0
    var isLoadingActive = false

    fun loadIcons(pageNum: Int, showTest: Boolean) {
        if (!ApiManager.hasNetwork(context)) {
            presenter?.onInternetConnectionError()
            return
        }
        if (currentDownloadPage <= maxPages) {
            presenter?.onShowProgress(showTest)
            isLoadingActive = true
            ApiManager.getIcons(pageNum, 30, RequestConstants.NEWEST, object : RequestCallBack {
                override fun onError(message: String?) {
                    isLoadingActive = false
                    presenter?.onHideProgress()
                }

                override fun onDataGet(icons: List<IconInfo>?) {
                    if (icons != null) {
                        currentDownloadPage++
                        presenter?.onDataUpdate(icons)
                    }
                    isLoadingActive = false
                    presenter?.onHideProgress()
                }

                override fun onResponseError(code: Int) {
                    presenter?.onHideProgress()
                    isLoadingActive = false
                }

                override fun onMetadataGet(metaData: MetaData?) {
                    if (metaData != null) {
                        maxPages = metaData.page_count
                    }
                    isLoadingActive = false
                }
            })
        }
    }

    fun retryLastRequest() {
        loadIcons(currentDownloadPage, true)
    }

    fun initPaginationListener(list: RecyclerView,  manager: LinearLayoutManager) {
        list.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = manager.childCount
                val totalItemCount = manager.itemCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
                if (!isLoadingActive && currentDownloadPage <= maxPages) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        loadIcons(currentDownloadPage, false)
                    }
                }
            }
        })
    }

    fun disposeRequest() {
        ApiManager.disposeRequest()
    }
}