package com.android.killqwerty.myapp.mynews.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.killqwerty.myapp.mynews.R
import com.android.killqwerty.myapp.mynews.adapters.NewsListAdapter
import com.android.killqwerty.myapp.mynews.data.response.Article
import com.android.killqwerty.myapp.mynews.data.response.Response
import com.android.killqwerty.myapp.mynews.viewmodels.NewsViewModel

class MainFragment : Fragment(){
    private lateinit var myIOnClickAdapterListener: IOnClickAdapterListener
    var mNewsViewModel : NewsViewModel? = null
    var myLiveDataResponse : MutableLiveData<Response>? = null
    var myLayoutManager : LinearLayoutManager? = null
    lateinit var recyclerView : RecyclerView
    var myList : List<Article>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_fragment,container,false)
        recyclerView = view.findViewById(R.id.recycler)
        init()
        return view
    }
    fun init(){
        myIOnClickAdapterListener = object : IOnClickAdapterListener{  //todo:
            override fun onClick(position: Int) {
                // myList!![position] обьект тут !
             //   Toast.makeText(context,"${myList!![position].publishedAt}",Toast.LENGTH_SHORT).show()
                //todo: я понял! тут же и так есть вьюмодель! так что от того что я добавлю туда одну лайвдату я и не просяду) , тут отправляем туды, во втором
                //фрагменте принимаю! а в активити пошлю только онклик, чтобы запустить второй фрагмент, а тот уже подцепит инфу из вьюмодели... гениально!
                (activity as? IShowArticle)?.showingArticle(myList!![position])

            }
        }
     //   val iOnClickAdapterListener = IOnClickAdapterListener
        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        myLiveDataResponse = mNewsViewModel!!.getResponseData()
        myLiveDataResponse!!.observe(this, Observer<Response> {
            myList = it.articles
            myLayoutManager = LinearLayoutManager(this.context)
            val myAdapter : RecyclerView.Adapter<*> = NewsListAdapter(myList!!,myIOnClickAdapterListener)
            recyclerView.apply {
                setHasFixedSize(false)
                layoutManager = myLayoutManager
                adapter = myAdapter
            }
        })

       // (activity as? IShowArticle).showingArticle()
    }
}