package com.android.killqwerty.myapp.mynews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.killqwerty.myapp.mynews.R

import com.android.killqwerty.myapp.mynews.adapters.NewsListAdapter
import com.android.killqwerty.myapp.mynews.data.response.Article
import com.android.killqwerty.myapp.mynews.data.response.Response
import com.android.killqwerty.myapp.mynews.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.main_fragment.view.*




class MainFragment : Fragment(){
    private lateinit var myIOnClickAdapterListener: IOnClickAdapterListener
    var mNewsViewModel : NewsViewModel? = null
    var myLiveDataResponse : MutableLiveData<Response>? = null
    var myLayoutManager : LinearLayoutManager? = null
    lateinit var recyclerView : RecyclerView
    var myList : List<Article>? = null
    var myView : View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (myView == null) {
            myView = inflater.inflate(R.layout.main_fragment, container, false)
            recyclerView = myView!!.findViewById(R.id.recycler)
            if(isNetworkAvailabel())
                init()
        }
        return myView
    }
    fun init(){
        mNewsViewModel = ViewModelProviders.of(activity!!).get(NewsViewModel::class.java)
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

        myIOnClickAdapterListener = object : IOnClickAdapterListener{
            override fun onClick(position: Int) {
                mNewsViewModel?.selectArticle(myList!![position])
                (activity as? IShowArticle)?.showingArticle()

            }
        }
    }
    fun isNetworkAvailabel(): Boolean { ///todo: тут должна быть проверка подключения
        if (true) {
            return true
        } else {
            myView?.false_conection_tv?.visibility = View.VISIBLE
            return false
        }
    }


}