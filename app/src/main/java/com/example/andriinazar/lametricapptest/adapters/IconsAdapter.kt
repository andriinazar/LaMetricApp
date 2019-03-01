package com.example.andriinazar.lametricapptest.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.andriinazar.lametricapptest.api.models.IconInfo
import com.example.andriinazar.lametricapptest.R
import com.example.andriinazar.lametricapptest.api.ApiManager
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.i_icon_list.view.*

class IconsAdapter (private val icons: ArrayList<IconInfo>, context: Context) : RecyclerView.Adapter<IconsAdapter.ViewHolder>() {

    private var context: Context = context

    override fun onCreateViewHolder(holeder: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.i_icon_list, holeder, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return icons.size
    }

    override fun onBindViewHolder(holeder: ViewHolder, position: Int) {
        holeder.bindItems(icons[position], context)
    }

    fun updateData(newIcons: ArrayList<IconInfo>) {
        icons.addAll(newIcons)
        notifyDataSetChanged()
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private val iconImage = view.iv_icon_image as ImageView
        private val iconName = view.tv_icon_name as TextView

        fun bindItems(iconInfo: IconInfo, context: Context) {
            if (ApiManager.hasNetwork(context)) {
                Picasso.get().load(iconInfo.thumb.xlarge).placeholder(android.R.mipmap.sym_def_app_icon).into(iconImage)
            } else {
                Picasso.get().load(iconInfo.thumb.xlarge).networkPolicy(NetworkPolicy.OFFLINE).into(iconImage)
            }

            iconName.text = iconInfo.title
        }
    }

}