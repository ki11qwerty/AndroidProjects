package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
    }
}