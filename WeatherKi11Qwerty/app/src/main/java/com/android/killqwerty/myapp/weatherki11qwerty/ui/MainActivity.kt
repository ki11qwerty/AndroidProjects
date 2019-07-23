package com.android.killqwerty.myapp.weatherki11qwerty.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.android.killqwerty.myapp.weatherki11qwerty.R
import com.android.killqwerty.myapp.weatherki11qwerty.data.ApiWeather
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherResponse
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.*
import retrofit2.Retrofit

class MainActivity : Activity() {
    lateinit var response : CurrentWeatherResponse
    private val weatherApi = ApiWeather()
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        my_btn.setOnClickListener { click() }
       // init()
    }
    fun init(){
        GlobalScope.launch(Dispatchers.Main){
            response = weatherApi.getCurrentWeather("Volgograd")
            //TODO = Registering an InstanceCreator with Gson for this type may fix this problem. похоже я тут надолго
            findViewById<TextView>(R.id.my_tv_1).text = response.currentWeatherEntry.toString()
            delay(100L)
            Log.d("myTag","after delay!")
        }
    }
    fun click(){
        GlobalScope.launch(Dispatchers.Main){
            Toast.makeText(this@MainActivity,"delay",Toast.LENGTH_SHORT).show()
            kotlinx.coroutines.delay(5000)
            Toast.makeText(this@MainActivity,"after delay",Toast.LENGTH_SHORT).show()
            response = weatherApi.getCurrentWeather("Volgograd")
            //TODO = Registering an InstanceCreator with Gson for this type may fix this problem. похоже я тут надолго
            findViewById<TextView>(R.id.my_tv_1).text = response.currentWeatherEntry.toString()
            delay(100L)
            Log.d("myTag","after delay!")
        }
    }
}