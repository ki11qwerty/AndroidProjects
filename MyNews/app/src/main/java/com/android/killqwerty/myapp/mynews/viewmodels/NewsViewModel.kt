package com.android.killqwerty.myapp.mynews.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.killqwerty.myapp.mynews.data.NewsAPI
import com.android.killqwerty.myapp.mynews.data.response.Article
import com.android.killqwerty.myapp.mynews.data.response.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel : ViewModel() {
    private var responseData : MutableLiveData<Response>? = null
    private var selected : MutableLiveData<Article> = MutableLiveData()

    fun getResponseData() = when(responseData){
        null -> {loadResponseData()}
        else -> {responseData}
    }

    private fun loadResponseData() : MutableLiveData<Response>? {
        if (responseData == null)
            responseData = MutableLiveData()
        try {

            CoroutineScope(Dispatchers.Default).launch { responseData?.postValue(NewsAPI.invoke().getAllNews()) }
        } catch (e: HttpException) {
            when (e.code()) {     // тут будет отлов ошибок
                in 200..300 -> {
                    Log.d("MYTAG", e.code().toString())
                }
                in 301..400 -> {
                    Log.d("MYTAG", e.code().toString())
                }
                in 401..499 -> {
                    Log.d("MYTAG", e.code().toString())
                }
                in 500..550 -> {
                    Log.d("MYTAG", e.code().toString())
                }
            }
        }
        return responseData
    }

    fun getSelectedArticle()= selected

    fun selectArticle(article: Article){
        selected.value = article
    }
}