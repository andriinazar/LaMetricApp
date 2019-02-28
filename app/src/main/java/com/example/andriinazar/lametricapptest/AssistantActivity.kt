package com.example.andriinazar.lametricapptest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_assistant.*

class AssistantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assistant)
        b_allow.setOnClickListener{
            val intent = Intent(this@AssistantActivity, IconsActivity::class.java)
            startActivity(intent)
        }
    }
}
