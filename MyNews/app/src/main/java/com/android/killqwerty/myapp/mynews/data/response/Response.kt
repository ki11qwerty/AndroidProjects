package com.android.killqwerty.myapp.mynews.data.response


data class Response(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)