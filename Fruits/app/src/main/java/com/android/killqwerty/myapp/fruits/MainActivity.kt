package com.android.killqwerty.myapp.fruits

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myString = ""
        setContentView(R.layout.main_activity)
        Toast.makeText(applicationContext, "${myString.length}",Toast.LENGTH_LONG).show()
        addSome()
        btn_send.setOnClickListener { onClick(it) }
        button_empty.setOnClickListener { onClick(it) }
    }

    fun onClick(view: View) {
        when (view.id) {
           btn_send.id -> {sendStringToViber()}
            button_empty.id -> {makeString()}
        }
    }

        fun sendStringToViber(){
            if(myString.isEmpty()){
                makeString()
            }
        val intent : Intent = Intent(ACTION_SEND)
        intent.setPackage("com.viber.voip")
        intent.type ="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, myString)
        startActivity(intent)
    }
    fun addSome() {
//        myArr.add("новая позиция")
//        myArr.add("и еще одна позиция")
//        myArr.add("ну и последняя")
        for (x in 0.rangeTo(myArr.size - 1)) {
            var step: Int = 1
            var SomeFruit = Fruit(myArr[x])
            if (SomeFruit.name.contentEquals("Мята") ||
                SomeFruit.name.contentEquals("Эстрагон") ){
                step = 100
                SomeFruit.weightValue = "гр"}
            if(SomeFruit.name.contentEquals("Сельдерей")) {
                SomeFruit.weightValue = "Уп"
            }
            val layout = layoutInflater.inflate(R.layout.view_element, null)

            layout.findViewById<TextView>(R.id.tv_name).text = SomeFruit.name
            layout.findViewById<TextView>(R.id.tv_count).text = "${SomeFruit.count} ${SomeFruit.weightValue}"
            layout.findViewById<Button>(R.id.btn_plus)
                .setOnClickListener { SomeFruit.count += step
                    layout.findViewById<TextView>(R.id.tv_count).text = "${SomeFruit.count} ${SomeFruit.weightValue}"}
            layout.findViewById<Button>(R.id.btn_minus)
                .setOnClickListener { if(SomeFruit.count > 0) SomeFruit.count -= step
                    layout.findViewById<TextView>(R.id.tv_count).text = "${SomeFruit.count} ${SomeFruit.weightValue}"}
            linear_in_scroll.addView(layout)
            listFruits.add(SomeFruit)

        }
    }

    fun makeString(){
        Log.d("MyT","makeString starts")
        myString = ""
        for (x in 0..(listFruits.size - 1)) {
            if (listFruits[x].count > 0) {
                Toast.makeText(applicationContext,"${listFruits[x].count}", Toast.LENGTH_LONG).show()
                myString += "${listFruits[x].name} - ${listFruits[x].count}${listFruits[x].weightValue} \n"
            }
        }
        Toast.makeText(applicationContext,"${myString}", Toast.LENGTH_LONG).show()
    }

    companion object {
        var myArr = mutableListOf<String>(
            "Апельсин", "Гранат", "Грейпрфрут", "Лайм", "Лимон","Мандарин","Миндаль", "Мята",
            "Сельдерей", "Эстрагон", "Яблоко"
        )
        var listFruits = mutableListOf<Fruit>()
        var myString = ""
    }
}
data class Fruit(var name:String, var count: Int = 0, var weightValue: String = "Кг")
