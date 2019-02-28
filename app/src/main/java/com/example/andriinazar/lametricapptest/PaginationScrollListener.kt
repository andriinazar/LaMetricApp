package com.example.andriinazar.lametricapptest

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager


abstract class PaginationScrollListener : RecyclerView.OnScrollListener() {

    var manager: LinearLayoutManager
    constructor(layoutManager: LinearLayoutManager) : this() {
        manager = layoutManager
    }
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

    }
}