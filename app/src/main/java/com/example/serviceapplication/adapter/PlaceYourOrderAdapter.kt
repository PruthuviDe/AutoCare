package com.demo.foodorderanddeliveryappkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.serviceapplication.R
import com.example.serviceapplication.model.AutoParts

class PlaceYourOrderAdapter(val autoPartsList: List<AutoParts?>?): RecyclerView.Adapter<PlaceYourOrderAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.placeyourorder_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(autoPartsList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if(autoPartsList == null) 0  else autoPartsList.size
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val thumbImage: ImageView = view.findViewById(R.id.thumbImage)
        val autopartName: TextView = view.findViewById(R.id.autopartName)
        val autopartPrice: TextView = view.findViewById(R.id.autopartPrice)
        val autopartQty: TextView = view.findViewById(R.id.autopartQty)

        fun bind(autoParts: AutoParts) {
            autopartName.text = autoParts?.name!!
            autopartPrice.text = "Price $" + String.format("%2f", autoParts?.price!! * autoParts.totalInCart)
            autopartQty.text = "Qty :" + autoParts?.totalInCart

            Glide.with(thumbImage)
                .load(autoParts?.url)
                .into(thumbImage)
        }
    }
}