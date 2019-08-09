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
            data!!.postValue(ApiWeather.invoke().getCurrentWeather("Волгоград").currentWeatherEntry)
        }.await()
    }
}