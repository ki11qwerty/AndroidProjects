package com.android.killqwerty.myapp.myapp3.Android2

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R
import kotlinx.android.synthetic.main.android2_lesson2.*

class Lesson2: Activity() {
    val text = "this! is! KOOOOOTLIN!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson2)
        setMyButtonsAndViews()
        someFun()

    }

    private fun setMyButtonsAndViews() {
        val exit = andr2_lesson2_btn_exit
        exit?.setOnClickListener { finish() }
        val tv1 = findViewById<TextView>(R.id.andr2_lesson2_tv1)
        tv1.text = text
        tv1.textSize = 24f
        tv1.setTextColor(ContextCompat.getColor(this,R.color.colorGreen))
        Toast.makeText(this,"this is my first activity with Kotlin", Toast.LENGTH_LONG).show()
    }
    private fun someFun(){
    }
}