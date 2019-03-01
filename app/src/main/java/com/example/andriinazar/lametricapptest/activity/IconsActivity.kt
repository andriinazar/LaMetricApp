package com.example.andriinazar.lametricapptest.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.andriinazar.lametricapptest.*
import com.example.andriinazar.lametricapptest.activity.icon_presenter.IIconActivityPresenter
import com.example.andriinazar.lametricapptest.activity.icon_presenter.IconActivityPresenterImpl
import com.example.andriinazar.lametricapptest.decorators.GridItemDecoration
import com.example.andriinazar.lametricapptest.adapters.IconsAdapter
import com.example.andriinazar.lametricapptest.api.models.IconInfo
import kotlinx.android.synthetic.main.activity_icons.*

class IconsActivity : AppCompatActivity() {

    private var adapter: IconsAdapter? = null
    private var presenterImpl: IconActivityPresenterImpl? = null
    private var manager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icons)
        initTabBar()
        initUI()
        initListeners()
    }

    private fun initTabBar() {
        tb_icons.title = getString(R.string.icon_activity_title)
        tb_icons.setTitleTextColor(ContextCompat.getColor(this@IconsActivity, android.R.color.white))
        setSupportActionBar(findViewById(R.id.tb_icons))
    }

    private fun initUI() {
        adapter = IconsAdapter(ArrayList(), this@IconsActivity)
        manager = GridLayoutManager(this@IconsActivity,3)
        rv_icon_list.layoutManager = manager
        rv_icon_list.addItemDecoration(GridItemDecoration(5, 3))
        rv_icon_list.adapter = adapter
    }

    private fun initListeners() {
        b_retry.setOnClickListener{
            presenterImpl?.retryLastRequest()
        }
    }

    private var listener: IIconActivityPresenter = object : IIconActivityPresenter {
        override fun onError(message: String) {
            showRetry()
        }

        override fun onInternetConnectionError() {
            showRetry()
        }

        override fun onDataUpdate(icons: List<IconInfo>) {
            hideRetry()
            updateList(icons)
        }

        override fun onShowProgress(showText: Boolean) {
            showProgress(showText)
        }

        override fun onHideProgress() {
            hideProgress()
        }
    }

    override fun onStart() {
        super.onStart()
        presenterImpl = IconActivityPresenterImpl(this@IconsActivity, listener)
        if (manager != null && rv_icon_list != null) {
            presenterImpl?.initPaginationListener(rv_icon_list, manager!!)
        }
        presenterImpl?.loadIcons(0, true)
    }

    override fun onStop() {
        super.onStop()
        presenterImpl?.disposeRequest()
    }

    private fun updateList(icons: List<IconInfo>) {
        adapter?.updateData(ArrayList(icons))
    }

    private fun showProgress(showText: Boolean) {
        if (showText) {
            tv_loading_message.visibility = View.VISIBLE
        }
        pb_spinner.visibility = View.VISIBLE
        ll_retry_container.visibility = View.GONE
    }

    private fun hideProgress() {
        pb_spinner.visibility = View.GONE
        tv_loading_message.visibility = View.GONE
    }

    private fun showRetry() {
        ll_retry_container.visibility = View.VISIBLE
        tv_loading_message.visibility = View.GONE
        rv_icon_list.visibility = View.GONE
        pb_spinner.visibility = View.GONE
    }

    private fun hideRetry() {
        ll_retry_container.visibility = View.GONE
        tv_loading_message.visibility = View.GONE
        rv_icon_list.visibility = View.VISIBLE
    }


}
