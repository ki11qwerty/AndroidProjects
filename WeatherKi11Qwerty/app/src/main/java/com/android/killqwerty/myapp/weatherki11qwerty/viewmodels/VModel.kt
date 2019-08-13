package com.android.killqwerty.myapp.weatherki11qwerty.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
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
//    suspend fun getForecastResponse():MutableLiveData<ForecastResponse> =when(forecastData){
//        null ->{
//            loadForecastData()
//            forecastData!!
//        }
//        else -> forecastData!!
//    }



    private suspend fun loadResponseData() {
        if(responseData == null)
            responseData = MutableLiveData()
        try {
            CoroutineScope(Dispatchers.Default).async {
                responseData!!.postValue(ApiWeather.invoke().getCurrentWeather(cityName))
            }.await()
        }catch (e: HttpException){
            when(e.code()){
                in 200..290 ->{}
                in 300..390 ->{}
                in 400..490 ->{  // сделаем вид что ошибка тут может быть только если ввели неверные город =)
                    // а так то сделать и нельзя,   ладно разберемся..   Toast.makeText(this,"$cityName - не найдено",Toast.LENGTH_SHORT)
                    cityName = defaultCityName
                    loadResponseData()
                }
                in 500..590 ->{}
            }
        }
    }
//    private suspend fun loadForecastData(){  а надо ли это мне? я ведь могу тупо в один метод оба запроса зафигачить... думаем
//        if(forecastData == null)
//            forecastData = MutableLiveData()
//        try {
//
//        }
//    }

    fun changeCity(newCityName : String){
        cityName = newCityName
        runBlocking {loadResponseData()}
    }
//    private suspend fun updateData(){
//        CoroutineScope(Dispatchers.Default).async {
//            responseData!!.postValue(ApiWeather.invoke().getCurrentWeather(cityName))
//        }.await()
//    }
    companion object{
    private const val defaultCityName = "Волгоград"
}
}