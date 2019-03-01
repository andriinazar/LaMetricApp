package com.example.andriinazar.lametricapptest.api

import com.example.andriinazar.lametricapptest.api.models.IconModel
import retrofit2.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/v2/icons")
    fun getIcons(
            @Query("page") page: Int,
            @Query("page_size") page_size: Int,
            @Query("order") newest: String) : Observable<Response<IconModel>>

}