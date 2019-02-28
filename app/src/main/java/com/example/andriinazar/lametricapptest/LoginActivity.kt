package com.example.andriinazar.lametricapptest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        b_login.setOnClickListener{
            val intent = Intent(this@LoginActivity, AssistantActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
