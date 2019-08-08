package com.android.killqwerty.myapp.weatherki11qwerty.viewmodels

import android.arch.lifecycle.ViewModel
import com.android.killqwerty.myapp.weatherki11qwerty.data.ApiWeather
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherEntry
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.Forecastday
import kotlinx.coroutines.*

class VModel : ViewModel() {
  //  private var forecastListModel : List<Forecastday>? = null
    private var currentWeatherEntry: CurrentWeatherEntry? = null


    suspend fun getCurrentWeatherEntry(): CurrentWeatherEntry{
        if (currentWeatherEntry == null){
            CoroutineScope(Dispatchers.Default).async {
                currentWeatherEntry = ApiWeather.invoke().getCurrentWeather("Волгоград").currentWeatherEntry
            }.await()
            simulateUpdate()
            return currentWeatherEntry!!

        }else{
            return currentWeatherEntry!!
        }

    }

suspend fun simulateUpdate(){
    CoroutineScope(Dispatchers.Default).launch {
        delay(5000)
        currentWeatherEntry!!.tempC = 100.0 }
}

}