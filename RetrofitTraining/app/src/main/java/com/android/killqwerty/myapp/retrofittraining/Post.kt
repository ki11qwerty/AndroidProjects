package com.android.killqwerty.myapp.retrofittraining

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title111: String,
    @SerializedName("body") val body: String
)