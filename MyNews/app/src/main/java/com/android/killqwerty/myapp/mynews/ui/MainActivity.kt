package com.android.killqwerty.myapp.mynews.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.android.killqwerty.myapp.mynews.R
import com.android.killqwerty.myapp.mynews.data.response.Article
import kotlinx.android.synthetic.main.mainactivity.*

class MainActivity : AppCompatActivity(), IShowArticle {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        //FragmentManager.beginTransaction().add(R.id.fragment,MainFragment()).commit()

    }

    override fun showingArticle(article: Article) {
       Toast.makeText(this,"${article.source.name}",Toast.LENGTH_SHORT).show()
    }
}