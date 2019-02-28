package com.example.andriinazar.lametricapptest

import android.R.id
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Authorization {

    @SerializedName("user_id")
    @Expose
    private var userId: String? = null

    @SerializedName("token")
    @Expose
    var token: String? = null

    val id: String
        get() = id

    fun setUserId(userId: String) {
        this.userId = userId
    }
}