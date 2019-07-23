//-------------------------------------------------------------------------
//             По существу:
// todo: сделать обработку ошибок в запросе
// todo: реализовать прогноз погоды на ближайшие дни
//
//            мелочь на дом
// todo: чуть подшаманить вьюшки, по красоте
// todo:
//
//
//
//
//
//
//-------------------------------------------------------------------------


package com.android.killqwerty.myapp.weatherki11qwerty.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import com.android.killqwerty.myapp.weatherki11qwerty.R
import com.android.killqwerty.myapp.weatherki11qwerty.data.ApiWeather
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.*

class MainActivity : Activity() {
    lateinit var response : CurrentWeatherResponse
    lateinit var myImage : ImageView
    var city : String = "Волгоград"
    private val weatherApi = ApiWeather()
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        myImage = findViewById(R.id.condition_icon)
        init()
        update_btn.setOnClickListener {
            Log.d("myTag","${city_et.text}")
            if(city_et.text.isNotEmpty())
                city = city_et.text.toString()
            init() }
    }
    fun init(){
        GlobalScope.launch(Dispatchers.Main){
            response = weatherApi.getCurrentWeather(city,"ru")
            Log.d("myTag","${response.currentWeatherEntry}")
            Log.d("myTag",response.location.country)
            location.text = "${response.location.country}," +
                    "${response.location.name}"
            condition_text.text = response.currentWeatherEntry.condition.text
            temp_c.text = response.currentWeatherEntry.tempC.toString() + " c"
            feels_like.text = "ощущается как ${response.currentWeatherEntry.feelslikeC}"
            last_update.text ="последние обновление:${response.currentWeatherEntry.lastUpdated}"

            val imageUrl = "https:${response.currentWeatherEntry.condition.icon}"
            Picasso.get()
                .load(imageUrl)
                .fit()
                .into(myImage)
        }
    }
//    fun click(){
//        GlobalScope.launch(Dispatchers.Main){
//            Toast.makeText(this@MainActivity,"delay",Toast.LENGTH_SHORT).show()
//            kotlinx.coroutines.delay(5000)
//            Toast.makeText(this@MainActivity,"after delay",Toast.LENGTH_SHORT).show()
//            response = weatherApi.getCurrentWeather("Volgograd")
//            //TODO = Registering an InstanceCreator with Gson for this type may fix this problem. похоже я тут надолго
//            findViewById<TextView>(R.id.my_tv_1).text = response.currentWeatherEntry.toString()
//            delay(100L)
//            Log.d("myTag","after delay!")
//        }
//    }
}