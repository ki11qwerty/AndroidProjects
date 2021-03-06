package com.android.killqwerty.myapp.retrofittraining

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : Activity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var myListOfPosts: List<User> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        findViewById<Button>(R.id.my_btn_users).setOnClickListener { conectingToUsers() }
    }
    fun conectingToUsers(){
        if (myListOfPosts.isEmpty()) {
            val postsApi = IApiService.create()
            val response = postsApi.getAllUsers()
            response.enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    myListOfPosts = response.body()!!
                    createView()
                    Log.d("callback", "onResponse: ${response.body()!!}")
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d("callBack", "onFailure")
                }
            })
        }else {
            Log.d("callback","it is else and not response")
            createView()
        }
    }
    fun createView(){
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myListOfPosts)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

//    override fun onSaveInstanceState(outState: Bundle?) {
//        outState.run {
//        }
//        super.onSaveInstanceState(outState)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {             такс, потом будет сохранение чтобы не делать запрос, пока не понял как проще то
//        savedInstanceState.getParcelableArrayList<User>()
//        super.onRestoreInstanceState(savedInstanceState)
//    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
