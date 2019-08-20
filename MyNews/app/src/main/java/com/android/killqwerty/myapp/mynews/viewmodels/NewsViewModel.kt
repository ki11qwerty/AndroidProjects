package com.android.killqwerty.myapp.mynews.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.killqwerty.myapp.mynews.data.NewsAPI
import com.android.killqwerty.myapp.mynews.data.response.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private var responseData : MutableLiveData<Response>? = null

    public fun getResponseData() = when(responseData){
        null -> {loadResponseData()}
        else -> {responseData}
    }

    private fun loadResponseData() : MutableLiveData<Response>{
        if(responseData == null)
            responseData = MutableLiveData<Response>()
        CoroutineScope(Dispatchers.Default).launch { responseData!!.postValue( NewsAPI.invoke().getAllNews()) }
        return responseData!!
    }
}