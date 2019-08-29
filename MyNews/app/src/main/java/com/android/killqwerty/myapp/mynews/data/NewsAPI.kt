package com.android.killqwerty.myapp.mynews.data

import com.android.killqwerty.myapp.mynews.data.response.Response
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val api_key = "efecdc64f5ff451e89046c36fa09f152"

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getHeadLines(
        @Query("country") country : String = "us"
    ) : Response

    @GET("everything")
    suspend fun getAllNews(
        @Query("q") q : String = "anything",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("from") from : String = "2019-08-20", // Это для примера, по хорошему я бы дату генерировал в вьюмодели
        @Query("pagesize") pageSize : Int = 20,
        @Query("page") page : Int = 1
    ) : Response


    companion object {
        operator fun invoke(): NewsAPI {
            val ceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apikey", api_key)
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
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsAPI::class.java)
        }
    }

}