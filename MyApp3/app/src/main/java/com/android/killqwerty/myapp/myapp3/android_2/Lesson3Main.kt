package com.android.killqwerty.myapp.myapp3.android_2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.killqwerty.myapp.myapp3.R
import kotlinx.android.synthetic.main.android2_lesson3_main.*

class Lesson3Main : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson3_main)
        setButtons()
    }
     fun setButtons(){
         andr2_les3_main_btn_service.setOnClickListener {
             val intent = Intent(this, Lesson3serv::class.java)
             startActivity(intent)
         }
//        andr2_les3_main_btn_broadcast.setOnClickListener {
//            val intent = Intent(Lesson3Main(), Lesson3broadcast::class.java)
//            startActivity(intent)
//        }
    }
}