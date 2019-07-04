package com.android.killqwerty.myapp.retrofittraining

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.my_text_view.view.*

class MyAdapter(private val userList: List<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val myLayout = LayoutInflater.from(parent.context)
           .inflate(R.layout.my_text_view,parent,false)
        return MyViewHolder(myLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("users","${userList[position].name}")
        holder.view.userid.text = "id -${userList[position].id.toString()}"
        holder.view.name.text = "name - ${userList[position].name}"
        holder.view.username.text = "user-name - ${userList[position].username}"
        holder.view.email.text = "email -${userList[position].email}"

    }

    override fun getItemCount() = userList.size
}