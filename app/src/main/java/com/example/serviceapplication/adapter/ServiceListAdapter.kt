package com.example.serviceapplication.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.serviceapplication.R
import com.example.serviceapplication.model.ServiceModel
import com.example.serviceapplication.ServiceViewActivity

class ServiceListAdapter (private val current_activity : Activity, private val service_list : ArrayList<ServiceModel>) : RecyclerView.Adapter<ServiceListAdapter.MyHolder2>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder2 {
        val itemView  = LayoutInflater.from(parent.context).inflate(
            R.layout.service_item, parent, false
        )
        return MyHolder2(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder2, position: Int) {
        val current_item = service_list[position]
        holder.service_name.text= current_item.serviceName
        holder.service_price.text = current_item.price
        Log.w("Included", current_item.included.toString())
        if(current_item.included!!.size != 0)  holder.lst_include.adapter = ArrayAdapter(holder.itemView.context,android.R.layout.simple_list_item_1, current_item.included!!)
        holder.itemView.setOnClickListener {
            val intent = Intent(current_activity, ServiceViewActivity::class.java)
            intent.putExtra("Data", current_item)
//            startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return service_list.size
    }

    inner class MyHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val service_name : TextView = itemView.findViewById(R.id.txt_name)
        val service_price : TextView = itemView.findViewById(R.id.txt_price)
        val lst_include : ListView = itemView.findViewById(R.id.lst_include)
    }
}