package com.android.killqwerty.myapp.retrofittraining

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface IApiService {
    // тут будет гет и тому подобное но потом
    @GET("users")
    fun getAllUsers() : Call<List<User>>

    @GET("posts")
    fun getAllPosts() : Call<List<Post>>

    companion object Factory {
        fun create(): IApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) // GsonBuilder().create()
                .build()
            return retrofit.create(IApiService::class.java)
        }
    }
}