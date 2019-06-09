package com.android.killqwerty.myapp.retrofittraining

import retrofit2.Retrofit

interface ApiService {
    // тут будет гет и тому подобное но потом
    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("а ты придумал какой апи будешь юзать?")
                .build()
                //.addCallAdapterFactory()
                //.addConverterFactory()
                //тут будет конвертер
                //тут будет адаптерфактори
            return retrofit.create(ApiService::class.java)
        }
    }
}