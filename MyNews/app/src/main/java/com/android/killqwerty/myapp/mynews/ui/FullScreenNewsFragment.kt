package com.android.killqwerty.myapp.mynews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.killqwerty.myapp.mynews.R
import com.android.killqwerty.myapp.mynews.data.response.Article
import com.android.killqwerty.myapp.mynews.viewmodels.NewsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.full_screen_news_fragment.*
import java.util.*

class FullScreenNewsFragment : Fragment() {
    private lateinit var mNewsViewModel : NewsViewModel
    private var article : MutableLiveData<Article>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.full_screen_news_fragment,container,false)
        init()
        return view
    }

    private fun init(){
        mNewsViewModel = ViewModelProviders.of(activity!!).get(NewsViewModel::class.java)
        article =  mNewsViewModel.getSelectedArticle()
        article?.observe(this, Observer<Article> {
            full_title_tv.text = it.title
            if (it.author != null)
                full_author_tv.text = it.author
            full_publishedby_tv.text = it.publishedAt
            Picasso.get()
                .load(it.urlToImage)
                .into(full_image)
            full_content_tv.text = it.content
            val newsUrl = it.url
            url_btn.setOnClickListener {
                (activity as? IShowArticle)?.openUrl(newsUrl)
            }
        })
    }
}