package com.android.killqwerty.myapp.mynews.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.killqwerty.myapp.mynews.data.NewsAPI
import com.android.killqwerty.myapp.mynews.data.response.Article
import com.android.killqwerty.myapp.mynews.data.response.Response
import com.android.killqwerty.myapp.mynews.utility.PAGE_SIZE
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
                        pageSize = PAGE_SIZE
                    )
                )
            }
        } catch (e: HttpException) {
            when (e.code()) {     // тут будем ловить ошибки с сетью
                in 200..299 -> {
                    Log.d("MYTAG", e.code().toString())
                }
                in 300..399 -> {
                    Log.d("MYTAG", e.code().toString())
                }
                in 404..499 -> {
                    Log.d("MYTAG", e.code().toString())
                }
                in 500..599 -> {
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