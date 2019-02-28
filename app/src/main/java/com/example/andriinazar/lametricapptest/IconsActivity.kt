package com.example.andriinazar.lametricapptest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_icons.*

class IconsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icons)
        tb_icons.title = getString(R.string.icon_activity_title)
        tb_icons.setTitleTextColor(ContextCompat.getColor(this@IconsActivity, android.R.color.white))
        setSupportActionBar(findViewById(R.id.tb_icons))
        initUI()
    }

    private fun initUI() {
        showProgress()
        var adapter = IconsAdapter(ArrayList(), this@IconsActivity)
        var manager = GridLayoutManager(this@IconsActivity,3)
        rv_icon_list.layoutManager = manager
        rv_icon_list.addItemDecoration(GridItemDecoration(5, 3))

        rv_icon_list.adapter = adapter

        ApiManager.getIcons(1, 30, RequestConstants.NEWEST, object : RequestCallBack{
            override fun onError(message: String?) {
                hideProgress()
            }

            override fun onDataGet(icons: List<IconInfo>?) {
                if (icons != null) {
                    adapter.updateData(ArrayList(icons))
                }
                hideProgress()
            }

            override fun onResponseError(code: Int) {
                hideProgress()
            }

        })
    }

    fun initPaginationListener(manager: LinearLayoutManager) {
        rv_icon_list.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = manager.childCount
                val totalItemCount = manager.itemCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
                if (!isLoading() && !isLastPage()) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        loadMoreItems()
                    }
                }
            }
        })
    }

    private fun showProgress() {
        pb_spinner.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pb_spinner.visibility = View.GONE
    }
}
