package com.android.killqwerty.myapp.weatherki11qwerty.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.android.killqwerty.myapp.weatherki11qwerty.R
import com.android.killqwerty.myapp.weatherki11qwerty.data.response.Forecastday
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.forecast_item.view.*

class AdapterForList(var myList : List<Forecastday>,var c: Context) : BaseAdapter() {
    lateinit var myView: View
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null){
            val imageUrl = "https:${myList[position].day.condition.icon}"
            myView = LayoutInflater.from(c).inflate(R.layout.forecast_item, null)
            myView.item_date_tv.text = myList[position].date
            myView.item_temp.text = "${myList[position].day.maxtempC} / ${myList[position].day.mintempC}"
            Picasso.get()
                .load(imageUrl)
                .resize(74,74)
                .into(myView.item_image)

        }else{
            myView = convertView
        }
        return myView
    }

    override fun getItem(position: Int): Any {
        return myList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return myList.size
    }
}