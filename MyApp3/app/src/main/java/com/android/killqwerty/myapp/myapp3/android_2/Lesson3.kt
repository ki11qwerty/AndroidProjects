package com.android.killqwerty.myapp.myapp3.android_2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.android.killqwerty.myapp.myapp3.R
import kotlinx.android.synthetic.main.android2_lesson3.*

class Lesson3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson3)
        setMyButtons()
    }

    fun setMyButtons() {
        andr2_lesson3_btn_exit.setOnClickListener { finish() }
        andr2_les3_btn_start.setOnClickListener { onClick(it) }
        andr2_les3_btn_stop.setOnClickListener { onClick(it) }
    }

    fun onClick(view: View) {
        when (view.id) {
            andr2_les3_btn_start.id -> startService(Intent(this, Lesson3_MyService::class.java))
            andr2_les3_btn_stop.id -> stopService(Intent(this, Lesson3_MyService::class.java))
        }
    }
}