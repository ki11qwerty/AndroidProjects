package com.android.killqwerty.myapp.mynews.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.killqwerty.myapp.mynews.data.NewsAPI
import com.android.killqwerty.myapp.mynews.data.response.Article
import com.android.killqwerty.myapp.mynews.data.response.Response
import kotlinx.coroutines.*
import retrofit2.HttpException

class NewsViewModel : ViewModel() {
    private var selected : MutableLiveData<Article> = MutableLiveData()
    private var everythingNewsData : MutableLiveData<Response>? = null

    fun getNews() : MutableLiveData<Response>? = when(everythingNewsData){
        null -> {
            everythingNewsData = MutableLiveData()
            loadNews(1)
            everythingNewsData
        }
        else -> {
            everythingNewsData
        }
    }

    private fun loadNews(getPage: Int) {
        try {
            CoroutineScope(Dispatchers.Default).launch {
                everythingNewsData?.postValue(
                    NewsAPI.invoke().getAllNews(
                        page = getPage,
                        pageSize = 10
                    )
                )
            }
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
    }

    fun getSelectedArticle() = selected

    fun selectArticle(article: Article) {
        selected.value = article
    }

    fun loadMore(nextPage: Int) {
        loadNews(nextPage)

    }
}