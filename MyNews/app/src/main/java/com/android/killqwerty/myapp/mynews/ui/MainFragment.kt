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
    private lateinit var ionClickAndLoadListener: IonClickAndLoadListener
    var mNewsViewModel : NewsViewModel? = null
    var myLiveDataResponse : MutableLiveData<Response>? = null
    var myLayoutManager : LinearLayoutManager? = null
    lateinit var recyclerView : RecyclerView
    var myList : MutableList<Article> = mutableListOf()
    var myView : View? = null
    lateinit var myAdapter : RecyclerView.Adapter<*>
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
        myLiveDataResponse = mNewsViewModel!!.getNews()
        myLiveDataResponse!!.observe(this, Observer<Response> {
            if(myList.isEmpty()) {
                myList.addAll(it.articles)// = it.articles
                myLayoutManager = LinearLayoutManager(this.context)
                myAdapter = NewsListAdapter(myList, ionClickAndLoadListener)
                recyclerView.apply {
                    setHasFixedSize(false)
                    layoutManager = myLayoutManager
                    adapter = myAdapter
                }
            }else{
                myList.addAll(it.articles)
                myAdapter.notifyDataSetChanged()
            }
        })

        ionClickAndLoadListener = object : IonClickAndLoadListener{
            override fun onClick(position: Int) {
                mNewsViewModel?.selectArticle(myList!![position])
                (activity as? IShowArticle)?.showingArticle()
            }
            override fun LoadMore(position: Int) {
                var nextPage = (position / 10) + 1

                mNewsViewModel?.loadMore(nextPage)
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