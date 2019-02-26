package com.example.andriinazar.lametricapptest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        b_sing_in.setOnClickListener{
            val intent = Intent(this@SignInActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}
