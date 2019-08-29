package com.android.killqwerty.myapp.mynews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.killqwerty.myapp.mynews.R
import com.android.killqwerty.myapp.mynews.data.response.Article
import com.android.killqwerty.myapp.mynews.ui.IonClickAndLoadListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListAdapter(val myList : List<Article>, val myListener: IonClickAndLoadListener) : RecyclerView.Adapter<NewsListAdapter.MyViewHolder>(), View.OnClickListener {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = myList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.news_item,
            parent,
            false
        ) as RelativeLayout
        layout.setOnClickListener(this)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.news_title.text = myList[position].title
        holder.itemView.tag = position

        var descriptionText: String? = myList[position].description
        if (descriptionText != null && descriptionText.length > 100)        // если длинна строки больше 100символов -> обрезаем -> ставим в конце три точки
            descriptionText = String.format("%s ...", descriptionText.substring(0, 100))
        holder.itemView.news_description.text = descriptionText
        if (myList[position].urlToImage?.length != 0) {
            Picasso.get()
                .load(myList[position].urlToImage)
                .resize(100, 100)
                .into(holder.itemView.news_image)
        }
          holder.itemView.tag = holder.adapterPosition
        if(holder.layoutPosition == itemCount - 1)
            myListener.LoadMore(itemCount)
    }

    override fun onClick(view: View?) {
        myListener.onClick( (view?.tag as Int))
    }
}
