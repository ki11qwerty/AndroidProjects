package com.android.killqwerty.myapp.weatherki11qwerty.data

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

const val api_key = "f3b6066e6df24b029e891754191407"
//https://api.apixu.com/v1/current.json?key=f3b6066e6df24b029e891754191407&q=Volgograd
interface ApiWeather{
    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") language: String = "en"
    )

    companion object{
    }//TODO: ты знаешь что нужно сделать
}