package com.android.killqwerty.myapp.weatherki11qwerty.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.killqwerty.myapp.weatherki11qwerty.data.ApiWeather
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherEntry
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.Forecastday
import kotlinx.coroutines.*

class VModel : ViewModel() {
    private var data: MutableLiveData<CurrentWeatherEntry>? = null
    // todo: удалить после теста строку ниже
    lateinit var current: CurrentWeatherEntry

    suspend fun getCurrentWeatherEntry(): LiveData<CurrentWeatherEntry> {
        if (data == null) {
            loadData()
            return data!!

        } else {
            return data!!
        }

    }

    private suspend fun loadData() {
        CoroutineScope(Dispatchers.Default).async {
            data = MutableLiveData()
            current = (ApiWeather.invoke().getCurrentWeather("Волгоград").currentWeatherEntry)  // del
            data!!.postValue(current)
        }.await()
        CoroutineScope(Dispatchers.Default).launch {
            doSomethingDirty()  // del
        }
    }

    suspend fun doSomethingDirty() { // это все ради примера, тренировка так сказать в бою
        for (x in 1..100) {
            delay(1000)
            current.tempC += 1
            data!!.postValue(current)
        }
    }
}