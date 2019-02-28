package com.example.andriinazar.lametricapptest

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthorizationInterceptor(private val apiService: ApiService, private val session: Session) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var mainResponse = chain.proceed(chain.request())
        val mainRequest = chain.request()

        if (session.isLoggedIn) {
            // if response code is 401 or 403, 'mainRequest' has encountered authentication error
            if (mainResponse.code() === 401 || mainResponse.code() === 403) {
                val authKey = getAuthorizationHeader(session.email, session.password)
                // request to login API to get fresh token
                // synchronously calling login API
                //val loginResponse = apiService.(authKey).execute()

                //val loginResponse = apiService.authorizeUser().execute()

                /*if (loginResponse.isSuccessful()) {
                    // login request succeed, new token generated
                    val authorization = loginResponse.body()
                    // save the new token
                    session.saveToken(authorization!!.getToken())
                    // retry the 'mainRequest' which encountered an authentication error
                    // add new token into 'mainRequest' header and request again
                    val builder = mainRequest.newBuilder().header("Authorization", session.token).method(mainRequest.method(), mainRequest.body())
                    mainResponse = chain.proceed(builder.build())
                }*/
            }
        }

        return mainResponse
    }

    companion object {

        /**
         * this method is API implemetation specific
         * might not work with other APIs
         */
        fun getAuthorizationHeader(email: String, password: String): String {
            val credential = "$email:$password"
            return "Basic " + Base64.encodeToString(credential.toByteArray(), Base64.DEFAULT)
        }
    }
}