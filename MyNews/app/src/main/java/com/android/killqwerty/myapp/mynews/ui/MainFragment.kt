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
  //  val iOnClickAdapterListener = IOnClickAdapterListener // todo: как ты это сука сделал
    lateinit var myIOnClickAdapterListener: IOnClickAdapterListener
    var mNewsViewModel : NewsViewModel? = null
    var myLiveDataResponse : MutableLiveData<Response>? = null
    var myLayoutManager : LinearLayoutManager? = null
    lateinit var recyclerView : RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_fragment,container,false)
        recyclerView = view.findViewById(R.id.recycler)
        init()
        return view
    }
    fun init(){
        myIOnClickAdapterListener = object : IOnClickAdapterListener{  //todo:
            override fun onClick(position: Int) {
                Toast.makeText(context,"$position это в ебаном фрагменте все? onClick Int",Toast.LENGTH_SHORT).show()
            }

            override fun onStringClick(str: String) {
                Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
            }
        }
     //   val iOnClickAdapterListener = IOnClickAdapterListener
        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        myLiveDataResponse = mNewsViewModel!!.getResponseData()
        myLiveDataResponse!!.observe(this, Observer<Response> {
            val myList = it.articles
            myLayoutManager = LinearLayoutManager(this.context)
            val myAdapter : RecyclerView.Adapter<*> = NewsListAdapter(myList,myIOnClickAdapterListener)
            recyclerView.apply {
                setHasFixedSize(false)
                layoutManager = myLayoutManager
                adapter = myAdapter
            }
        })

       // (activity as? IShowArticle).showingArticle()
    }
}