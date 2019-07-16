package com.android.killqwerty.myapp.weatherki11qwerty.data

import com.android.killqwerty.myapp.weatherki11qwerty.data.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val api_key = "f3b6066e6df24b029e891754191407"
//https://api.apixu.com/v1/current.json?key=f3b6066e6df24b029e891754191407&q=Volgograd
interface ApiWeather{
    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") language: String = "en"
    ) : Deferred<CurrentWeatherResponse>

    companion object{
    operator fun invoke() : ApiWeather{
        val ceptor = Interceptor{chain ->
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
            //.addCallAdapterFactory() //TODO короче сил нет думать сегодня, https://medium.com/nuances-of-programming/android-networking-%D0%B2-2019-retrofit-%D1%81-kotlin-coroutines-7254e68ca4a4
            // вот там поискать завтра ответы, как с депрекейтед КоротинсАдаптерФактори быть...
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiWeather::class.java)

    }}
}