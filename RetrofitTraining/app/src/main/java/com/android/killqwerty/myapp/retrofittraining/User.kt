package com.android.killqwerty.myapp.retrofittraining

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String)
//) : Parcelable{
//    override fun writeToParcel(out: Parcel?, p1: Int) {
//
//    }
//}