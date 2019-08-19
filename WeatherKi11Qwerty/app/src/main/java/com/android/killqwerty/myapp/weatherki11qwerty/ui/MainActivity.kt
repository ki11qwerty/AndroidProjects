//----------|--------------------|--------------------------------------------------------------------------------------
//          |    По существу:    |
//          |--------------------|
// готово 1 - сделать обработку ошибок в запросе
// готово 2 - реализовать прогноз погоды на ближайшие дни
// готово 3 - реализовать список погоды
// готово 4 - подшаманить архитектуру, LiveData или что то попроще, добавить слой ...
// todo: 5-  добавить локальное сохранение предыдущих показаний, проверять последнее обновление   ..
//      + показывать старую информацию  если нет сети (Room - настало твое время)
// todo: 6 - после добавления дата слоя, реализовать сервис который будет обновлять информацию хотя бы рад в день
// todo: 7 - добавить виджет , с состоянием погоды и обновлении вместе с сервисом
// todo: 8 - реализовать боковое меню
// todo: 9 - Repository слой - надо ли?
// todo: - ROOM
// todo: - Injection
// todo: - пройтись по фрагментам

//--------|------------------------|------------------------------------------------------------------------------------
//        |    мелкие ништячки:    |
//        |------------------------|
//готово: дома если будет время, пройтись по коду и вернуть все функции на место, но под новую модель
//готово: добавить обработку ошибок сети в VModel
//todo: ошибки в vModel ловить можно, а показать об этом лучше как? думаю допилить liveData<String> и
//     выводить во вью какое то время. возможно в Тоасте
//----------------------------------------------------------------------------------------------------------------------


package com.android.killqwerty.myapp.weatherki11qwerty.ui

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.*
import com.android.killqwerty.myapp.weatherki11qwerty.R
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherResponse
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.ForecastResponse
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.Forecastday
import com.android.killqwerty.myapp.weatherki11qwerty.viewmodels.VModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var myImage: ImageView
    lateinit var conditionIconUrl: String
    lateinit var forecastListView: ListView
    lateinit var adapterForList: AdapterForList
    private var forecastList: MutableList<Forecastday>? = null

    lateinit var currentData: LiveData<CurrentWeatherResponse>
    lateinit var forecastData: LiveData<ForecastResponse>
    lateinit var myModel: VModel

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
    }
    private fun init() {
        GlobalScope.launch(Dispatchers.Main) {
            myModel = ViewModelProviders.of(this@MainActivity).get(VModel::class.java)
            currentData = myModel.getCurrentWeatherResponse()
            currentData.observe(this@MainActivity, Observer<CurrentWeatherResponse> {
                if (it != null) {
                    temp_c.text = it.currentWeatherEntry.tempC.toString()
                    location.text = String.format("%s,%s",it.location.country,it.location.name)
                    condition_text.text = it.currentWeatherEntry.condition.text
                    feels_like.text = String.format("ощущается как %.1f",it.currentWeatherEntry.feelslikeC)
                    last_update.text = String.format("последние обновление: %s",it.currentWeatherEntry.lastUpdated)
                    conditionIconUrl = "https:${it.currentWeatherEntry.condition.icon}"
                    Picasso.get()
                        .load(conditionIconUrl)
                        .fit()
                        .into(myImage)
                }
            })
            forecastData = myModel.getForecastResponse()
            forecastData.observe(this@MainActivity, Observer<ForecastResponse> {
                if (it != null) {
                    forecastList = mutableListOf()
                    for (item in it.forecast.forecastday) {
                        forecastList?.add(item)
                    }
                    adapterForList = AdapterForList(forecastList!!, applicationContext)
                    forecastListView.adapter = adapterForList
                }
            })
            update_btn.setOnClickListener {
                if (city_et.length() > 1)
                    myModel.changeCity(city_et.text.toString())
            }

        }
    }
}





//"открытия века для Алёши" :
//1 - исключения в корутинах надо ловить в самом потоке, а не оборачивать его =)