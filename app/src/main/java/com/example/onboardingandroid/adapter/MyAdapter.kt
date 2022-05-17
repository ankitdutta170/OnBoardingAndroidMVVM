package com.example.onboardingandroid.adapter



import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingandroid.R

import com.example.onboardingandroid.models.UserItem


import kotlinx.android.synthetic.main.card_item.view.*
import org.w3c.dom.Text

class MyAdapter(val context: Context, private val itemList:List<UserItem>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var email_id:TextView
        var name:TextView
        var age:TextView
        var ph_no:TextView
        init{
            email_id = itemView.email_Id
            name = itemView.name
            age = itemView.age
            ph_no =itemView.ph_no
        }
    }
    var lists = mutableListOf<UserItem>()
    fun setList(list:List<UserItem>){
        this.lists = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.card_item,parent,false)
        return ViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = lists[position]
        holder.email_id.text = list.email
        holder.name.text = list.name
        holder.age.text = list.age.toString()
        holder.ph_no.text = list.ph_no.toString()

        // val view = R.id.circle
        //var color = Color.parseColor("AE6118")




    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}