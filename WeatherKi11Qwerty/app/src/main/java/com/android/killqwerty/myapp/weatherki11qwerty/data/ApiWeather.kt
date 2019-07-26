package com.android.killqwerty.myapp.weatherki11qwerty.data

import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherResponse
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.ForecastResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val api_key = "f3b6066e6df24b029e891754191407"

//https://api.apixu.com/v1/current.json?key=f3b6066e6df24b029e891754191407&q=Volgograd
//https://api.apixu.com/v1/forecast.json?key=f3b6066e6df24b029e891754191407&q=Волгоград&days=10
interface ApiWeather {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") language: String = "en"
    ): CurrentWeatherResponse

    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("q") location: String,
        @Query("days") days: Int = 7,
        @Query("lang") language: String = "en"
    ): ForecastResponse

    companion object {
        operator fun invoke(): ApiWeather {
            val ceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", api_key)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiWeather::class.java)
        }
    }
}