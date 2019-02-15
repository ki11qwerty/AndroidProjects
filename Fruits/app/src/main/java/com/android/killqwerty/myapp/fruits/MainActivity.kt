package com.android.killqwerty.myapp.fruits

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_activity2.*
import java.util.zip.Inflater

class MainActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity2)
        //btn_send.setOnClickListener { onClick(it) }
        btn_add.setOnClickListener { onClick(it) }
    }
    fun onClick(view: View){
        when(view.id){
//            btn_send.id -> toViber()
            btn_add.id -> addSome()
        }
    }
//    fun toViber(){
//        val intent = Intent(ACTION_SEND)
//        intent.setPackage("com.viber.voip");
//        intent.type ="text/plain"
//        intent.putExtra(Intent.EXTRA_TEXT, "Работает Не?")
//        startActivity(intent)
//    }
    fun addSome(){
        setContentView(R.layout.main_activity2)
        for(x in 0..10){
            var NewFruit = Fruit(myArr[x])
            var layout = layoutInflater.inflate(R.layout.view_element,null)
            layout.findViewById<TextView>(R.id.tv_name).text = NewFruit.name
            layout.findViewById<Button>(R.id.btn_plus).setOnClickListener { Toast.makeText(applicationContext, "plus", Toast.LENGTH_LONG).show() }
            layout.findViewById<Button>(R.id.btn_minus).setOnClickListener { Toast.makeText(applicationContext, "minus", Toast.LENGTH_LONG).show() }
            linear_in_scroll.addView(layout)

        }
    }
    companion object {
        val myArr = listOf("лимон","Апельсин","грейпрфрут","лайм","яблоко","сельдерей","гранат",
            "мандарин","мята","эстрагон","миндаль")
    }
}

data class Fruit(var name:String)
