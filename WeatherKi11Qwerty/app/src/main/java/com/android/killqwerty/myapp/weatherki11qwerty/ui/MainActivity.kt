//-------------------------------------------------------------------------
//             По существу:
// готово 1 - сделать обработку ошибок в запросе
// готово 2 - реализовать прогноз погоды на ближайшие дни
// готово 3 - реализовать список погоды
// готово 4 - подшаманить архитектуру, LiveData или что то попроще, добавить слой ...
// todo: 5-  добавить локальное сохранение предыдущих показаний, проверять последнее обновление   .. + показывать старую информацию  если нет сети
// todo: 6 - после добавления дата слоя, реализовать сервис который будет обновлять информацию хотя бы рад в день
// todo: 7 - добавить виджет , с состоянием погоды и обновлении вместе с сервисом
// todo: 8 - реализовать боковое меню
//
//todo: дома если будет время, пройтись по коду и вернуть все функции на место, но под новую модель
//todo: добавить обработку ошибок сети в VModel
//todo: ошибки в vModel ловить можно, а показать об этом лучше как? думаю допилить liveData<String> и выводить во вью какое то время.
//-------------------------------------------------------------------------


package com.android.killqwerty.myapp.weatherki11qwerty.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.*
import com.android.killqwerty.myapp.weatherki11qwerty.R
import com.android.killqwerty.myapp.weatherki11qwerty.data.ApiWeather
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherEntry
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherResponse
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.ForecastResponse
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.Forecastday
import com.android.killqwerty.myapp.weatherki11qwerty.viewmodels.VModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.*
import retrofit2.HttpException

class MainActivity : AppCompatActivity(){
    lateinit var myImage: ImageView
    lateinit var forecastListView: ListView
    lateinit var adapterForList: AdapterForList
    private val weatherApi = ApiWeather()
    lateinit var condndionIconUrl: String

    lateinit var myData : LiveData<CurrentWeatherResponse>
    lateinit var myModel : VModel

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        forecastListView = findViewById(R.id.my_forecast_list)
        myImage = findViewById(R.id.condition_icon)
        init()
//        update_btn.setOnClickListener {
//            Log.d("myTag", "${city_et.text}")
//            if (city_et.text.isNotEmpty())
//                city = city_et.text.toString()
//            init()
//        }
    }

    @SuppressLint("SetTextI18n") // иду  на это осознанно так как наврятли я это приложение буду переводить на другие языки, и суть учебы на данный момент совсем в другом
    fun init() {
        GlobalScope.launch(Dispatchers.Main) {
            myModel = ViewModelProviders.of(this@MainActivity).get(VModel::class.java)
            myData = myModel.getCurrentWeatherResponse()
            myData.observe(this@MainActivity, Observer<CurrentWeatherResponse> {
                if (it != null) {
                    temp_c.text = it.currentWeatherEntry.tempC.toString()
                    location.text = "${it.location.country},${it.location.name}"
                    condition_text.text = it.currentWeatherEntry.condition.text
                    feels_like.text = "ощущается как ${it.currentWeatherEntry.feelslikeC}"
                    last_update.text = "последние обновление:${it.currentWeatherEntry.lastUpdated}"
                    condndionIconUrl = "https:${it.currentWeatherEntry.condition.icon}"
                    Picasso.get()
                        .load(condndionIconUrl)
                        .fit()
                        .into(myImage)
                }
                update_btn.setOnClickListener{
                    if(city_et != null)
                        myModel.changeCity(city_et.text.toString())
                }
            })



        }






//        GlobalScope.launch(Dispatchers.Main) {
//            try {
//                weatherResponse = weatherApi.getCurrentWeather(city, "ru")
//                forecastResponse = weatherApi.getForecastWeather(city, language = "ru")
//            } catch (e: HttpException) {                                // ловим и тостим ошибку, ставим город по умолчанию, делаем запрос повторно
//                when (e.code()) {                                       // оставлю WHEN если понадобится обработать разные ошибки после
//                    in 400..451 -> {
//                        Toast.makeText(applicationContext, "ошибка клиента : ${e.code()}", Toast.LENGTH_LONG).show()
//                        city = defaultCity
//                        init()
//                    }
//                    in 500..507 -> Toast.makeText(
//                        applicationContext,
//                        "ошибка сервера : ${e.code()}",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//            Log.d("myTag", "${weatherResponse.currentWeatherEntry}")
//            Log.d("myTag", weatherResponse.location.country)
//            location.text = "${weatherResponse.location.country},${weatherResponse.location.name}"
//            condition_text.text = weatherResponse.currentWeatherEntry.condition.text
//            temp_c.text = weatherResponse.currentWeatherEntry.tempC.toString() + " c"
//            feels_like.text = "ощущается как ${weatherResponse.currentWeatherEntry.feelslikeC}"
//            last_update.text = "последние обновление:${weatherResponse.currentWeatherEntry.lastUpdated}"
//
//            val imageUrl = "https:${weatherResponse.currentWeatherEntry.condition.icon}"
//            Picasso.get()
//                .load(imageUrl)
//                .fit()
//                .into(myImage)
//
//            Log.d("MYTAG", "${forecastResponse.forecast.forecastday.size}")
//
//            var myList: MutableList<Forecastday> = mutableListOf()
//            for (list in forecastResponse.forecast.forecastday) {
//                myList.add(list)
//            }
//            adapterForList = AdapterForList(myList, applicationContext)
//            forecastListView.adapter = adapterForList
//
//
//        }
    }
}




// далее рубрика - это работает! но почему не понятно... (разобраться потом на выходных, сейчас не критично)
//
// 1 - в чем отличие Activity от AppCompatActivity, почему он подходит под viewModelProviders.of() а активити нет.
// 2 - брать ли весь обьект ответа из вьюМодели или создавать кучу лайвов для каждой позиции
//         а если отдавать весь запрос, то где организовать Picasso чтобы не делать кучу запросов в сеть...