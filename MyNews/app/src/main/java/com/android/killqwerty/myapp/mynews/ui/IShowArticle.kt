package com.android.killqwerty.myapp.mynews.ui

import com.android.killqwerty.myapp.mynews.data.response.Article

interface IShowArticle { // todo: названия умнее просто не придумал XD
    fun showingArticle(article: Article) //TODO: put one article class for send it in second fragment
}