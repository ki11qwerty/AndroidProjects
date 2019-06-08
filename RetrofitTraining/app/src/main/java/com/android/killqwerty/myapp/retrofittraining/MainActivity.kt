package com.android.killqwerty.myapp.retrofittraining

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        findViewById<TextView>(R.id.main_tv).text = "RetrofitTraining\n начнется завтра =)"
        findViewById<Button>(R.id.main_btn1).setOnClickListener {
            Toast.makeText(this,"Чпонь",Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroy() {
        Toast.makeText(this,"booom!",Toast.LENGTH_LONG).show()
        super.onDestroy()
    }
}