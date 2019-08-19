package com.android.killqwerty.myapp.weatherki11qwerty.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.android.killqwerty.myapp.weatherki11qwerty.data.ApiWeather
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherResponse
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.ForecastResponse
import kotlinx.coroutines.*
import retrofit2.HttpException

class VModel : ViewModel() {
    private var responseData: MutableLiveData<CurrentWeatherResponse>? = null
    private var forecastData: MutableLiveData<ForecastResponse>? = null
    var cityName: String = defaultCityName

    suspend fun getCurrentWeatherResponse()= when(responseData){
            null ->{
                loadResponseData()
                responseData!!
            }
            else -> responseData!!
        }
    suspend fun getForecastResponse():MutableLiveData<ForecastResponse> =when(forecastData){
        null -> {
            loadForecastData()
            forecastData!!
        }
        else -> forecastData!!
    }


    private suspend fun loadResponseData() {
        if (responseData == null)
            responseData = MutableLiveData()
        CoroutineScope(Dispatchers.Default).launch {
            try {
                responseData!!.postValue(ApiWeather.invoke().getCurrentWeather(cityName, "ru"))
            } catch (e: HttpException) {
                Log.d("MyTag", "$e")
                findException(e.code())
            }
        }
    }

    private suspend fun loadForecastData() {
        if (forecastData == null)
            forecastData = MutableLiveData()
        CoroutineScope(Dispatchers.Default).launch {
            try {
                forecastData!!.postValue(ApiWeather.invoke().getForecastWeather(cityName))
            } catch (e: HttpException) {
                Log.d("MyTag", "$e ")
                findException(e.code())
            }
        }
    }

    fun changeCity(newCityName : String){
        cityName = newCityName
        runBlocking {
            loadResponseData()
            loadForecastData()
        }
    }
    private suspend fun findException(exception: Int){
        when(exception){
            in 200..290 ->{}
            in 300..390 ->{}
            in 399..490 ->{  // сделаем вид что ошибка тут может быть только если ввели неверные город =)
                if(cityName != defaultCityName) {
                    wrongCityName()
                }
            }
            in 500..590 ->{
                delay(5000)
                changeCity(cityName)
            }
        }
    }
    private fun wrongCityName(){
        if(cityName != defaultCityName){
            changeCity(defaultCityName)
        }
    }

    companion object{
    private const val defaultCityName = "Волгоград"
}
}