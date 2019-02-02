package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.killqwerty.myapp.myapp3.R
import kotlinx.android.synthetic.main.android2_lesson2.*

class Lesson2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson2)
        setMyButtons()
    }

    fun setMyButtons() {
        andr2_lesson2_btn_exit.setOnClickListener { finish() }
    }
}