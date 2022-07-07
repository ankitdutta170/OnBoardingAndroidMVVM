package com.example.onboardingandroid.adapter



import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingandroid.R
import com.example.onboardingandroid.ShowProfileActivity

import com.example.onboardingandroid.models.UserItem


import kotlinx.android.synthetic.main.card_item.view.*

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

        holder.itemView.setOnClickListener {
            val name = list.name
            val age = list.age.toString()
            val ph_no = list.ph_no.toString()
            val email = list.email.toString()

            navigateToShowProfileActivity(email,name,age,ph_no)


        }

        // val view = R.id.circle
        //var color = Color.parseColor("AE6118")




    }

    private fun navigateToShowProfileActivity(email:String,name: String, age: String, phNo: String) {
        val intent = Intent(context, ShowProfileActivity::class.java)
        intent.putExtra("name",name)
        intent.putExtra("age",age)
        intent.putExtra("phNo",phNo)
        intent.putExtra("email",email)
        startActivity(context,intent,null)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}