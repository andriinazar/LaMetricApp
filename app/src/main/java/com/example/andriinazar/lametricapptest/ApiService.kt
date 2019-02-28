package com.example.andriinazar.lametricapptest

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import java.util.*

interface ApiService {

    @GET ("api/v2/oauth2/authorize/")
    fun authorizeUser() : Observable<Response<Unit>>
}