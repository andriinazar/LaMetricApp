package com.example.andriinazar.lametricapptest.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.andriinazar.lametricapptest.BuildConfig
import com.example.andriinazar.lametricapptest.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadUrl()
    }

    private fun loadUrl() {
        // Magic !!!
        var url = BuildConfig.BASE_URL + "/api/v2/oauth2/authorize/?" + "client_id=" + BuildConfig.CLIENT_ID + "&redirect_uri=icon_activity&response_type=code&scope="
        wv_login_low.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: WebResourceRequest?): Boolean {
                // Magic !!!
                if (url?.url.toString().contains("code=")) {
                    startIconActivity()
                } else if (url?.url.toString().contains("state=")) {
                    return true
                } else if (url?.url.toString().contains("logout")) {
                    view?.loadUrl(url?.url.toString())
                    startSingInActivity()
                    return true
                }
                view?.loadUrl(url?.url.toString())
                return true
            }
        }
        wv_login_low.loadUrl(url)
    }


    fun startIconActivity() {
        val intent = Intent(this@LoginActivity, IconsActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun startSingInActivity() {
        val intent = Intent(this@LoginActivity, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}
