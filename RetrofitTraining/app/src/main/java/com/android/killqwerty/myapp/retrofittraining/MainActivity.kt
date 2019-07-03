package com.android.killqwerty.myapp.retrofittraining

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val postsApi = IApiService.create()
        val response =  postsApi.getAllPosts()
        response.enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
              //  doSomething(response.body()!!)       передать в метод ответ
                Log.d("callback","onResponse: ${response.body()!![99]}\n\n ${response.body()!!.lastIndex}")
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("callBack","onFailure")
            }
        })
    }
//    fun doSomething(list : List<Post>){    тут будет обработка ответа и заполнение вьюхи а пока учу ресайклер =)
//
//    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
