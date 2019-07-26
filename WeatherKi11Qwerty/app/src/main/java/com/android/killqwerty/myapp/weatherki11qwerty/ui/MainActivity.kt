//-------------------------------------------------------------------------
//             По существу:
// готово! - сделать обработку ошибок в запросе
// todo: реализовать прогноз погоды на ближайшие дни
//
//            мелочь на дом
// todo: чуть подшаманить вьюшки, по красоте
//-------------------------------------------------------------------------


package com.android.killqwerty.myapp.weatherki11qwerty.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.android.killqwerty.myapp.weatherki11qwerty.R
import com.android.killqwerty.myapp.weatherki11qwerty.data.ApiWeather
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.*
import retrofit2.HttpException

class MainActivity : Activity() {
    lateinit var response : CurrentWeatherResponse
    lateinit var myImage : ImageView
    var city : String = "Волгоград"
    val defaultCity : String = "Волгоград"
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
    @SuppressLint("SetTextI18n") // иду  на это осознанно так как наврятли я это приложение буду переводить на другие языки, и суть учебы на данный момент совсем в другом
    fun init() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                response = weatherApi.getCurrentWeather(city, "ru")
            } catch (e: HttpException) {                                // ловим и тостим ошибку, ставим город по умолчанию, делаем запрос повторно
                when (e.code()) {                                       // оставлю WHEN если понадобится обработать разные ошибки после
                    in 400..451 -> {
                        Toast.makeText(applicationContext, "ошибка клиента : ${e.code()}", Toast.LENGTH_LONG).show()
                        city = defaultCity
                        init()
                    }
                    in 500..507 -> Toast.makeText(applicationContext, "ошибка сервера : ${e.code()}", Toast.LENGTH_LONG).show()
                }
            }
            Log.d("myTag", "${response.currentWeatherEntry}")
            Log.d("myTag", response.location.country)
            location.text = "${response.location.country},${response.location.name}"
            condition_text.text = response.currentWeatherEntry.condition.text
            temp_c.text = response.currentWeatherEntry.tempC.toString() + " c"
            feels_like.text = "ощущается как ${response.currentWeatherEntry.feelslikeC}"
            last_update.text = "последние обновление:${response.currentWeatherEntry.lastUpdated}"

            val imageUrl = "https:${response.currentWeatherEntry.condition.icon}"
            Picasso.get()
                .load(imageUrl)
                .fit()
                .into(myImage)
        }
    }
}