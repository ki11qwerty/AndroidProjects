package com.android.killqwerty.myapp.mynews.adapters

import android.app.Application
import android.content.ContentProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.killqwerty.myapp.mynews.R
import com.android.killqwerty.myapp.mynews.data.response.Article
import com.android.killqwerty.myapp.mynews.ui.IOnClickAdapterListener
import com.android.killqwerty.myapp.mynews.ui.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListAdapter(val myList : List<Article>, val myListener: IOnClickAdapterListener) : RecyclerView.Adapter<NewsListAdapter.MyViewHolder>(), View.OnClickListener {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = myList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.news_item,
            parent,
            false
        ) as RelativeLayout
        layout.setOnClickListener(this)  // сами обработаем прям тут без анонимных свалок
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.news_title.text = myList[position].title
        holder.itemView.tag = position     // пихаем норер позиции в тэг

        var descriptionText: String? = myList[position].description
        if (descriptionText != null && descriptionText.length > 100)        // если длинна строки больше 100символов -> обрезаем -> ставим в конце три точки
            descriptionText = String.format("%s ...", descriptionText.substring(0, 100))
        holder.itemView.news_description.text = descriptionText

        Picasso.get()
            .load(myList[position].urlToImage)
            .resize(100, 100)
            .into(holder.itemView.news_image)



          holder.itemView.tag = holder.adapterPosition  // а если вот так вот...
    }

    override fun onClick(view: View?) {
        Log.d("MYTAG","адаптер клик")

        myListener.onClick( (view?.tag as Int))             // фича или хрень завтра разберусь..
    }
}
