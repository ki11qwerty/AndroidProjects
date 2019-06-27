package com.android.killqwerty.myapp.retrofittraining

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
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
        response.subscribe {
            Log.d("MYTAG", "${it.size}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
