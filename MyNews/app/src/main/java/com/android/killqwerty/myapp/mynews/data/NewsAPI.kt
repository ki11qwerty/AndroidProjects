package com.android.killqwerty.myapp.mynews.data

import com.android.killqwerty.myapp.mynews.data.response.Response
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Your API key is: efecdc64f5ff451e89046c36fa09f152      tasuke34!
//a22b05cb93b44860ae0d430583ef82ee               tasuke134!
const val api_key = "a22b05cb93b44860ae0d430583ef82ee"     // от таске 134

//curl https://newsapi.org/v2/top-headlines -G \
//-d country=us \
//-d apiKey=a22b05cb93b44860ae0d430583ef82ee
//https://newsapi.org/v2/top-headlines?country=us&apikey=a22b05cb93b44860ae0d430583ef82ee


//остался один день братан !!!
    // https://newsapi.org/v2/everything?q=bitcoin&pagesize=2&page=10&from=2019-07-28&sortBy=publishedAt&apiKey=a22b05cb93b44860ae0d430583ef82ee вот в эту сторону рой
// pagesize
//paze
//sortBy=publishedAt
//
//я думаю идея в том, чтобы делать кусковые запросы увеличивая номер страницы, потом получать ответ в список, добавлять список в твой список, дергать обнову ресаайклера
    //завтра надо сдать харош уже
interface NewsAPI {

    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") country : String = "us",
        @Query("limit") limit : Int = 3
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