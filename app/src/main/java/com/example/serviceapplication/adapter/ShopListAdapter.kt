package com.example.serviceapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.serviceapplication.R
import com.example.serviceapplication.model.Hours
import com.example.serviceapplication.model.ShopModel
import java.text.SimpleDateFormat
import java.util.*

class ShopListAdapter(val shopList: List<ShopModel?>?, val clickListener: ShopListClickListener): RecyclerView.Adapter<ShopListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ShopListAdapter.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recylcer_shop_list_row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListAdapter.MyViewHolder, position: Int) {
        holder.bind(shopList?.get(position))
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(shopList?.get(position)!!)
        }
    }

    override fun getItemCount(): Int {
        return shopList?.size!!
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        val thumbImage: ImageView = view.findViewById(R.id.thumbImage)
        val txt_shop_name: TextView = view.findViewById(R.id.txt_shop_name)
        val txt_shop_address: TextView = view.findViewById(R.id.txt_shop_address)
        val txt_shop_hours: TextView = view.findViewById(R.id.txt_shop_hours)

        fun bind(shopModel: ShopModel?){
            txt_shop_name.text = shopModel?.name
            txt_shop_address.text = "Address: "+shopModel?.address
            txt_shop_hours.text = "Today's Hours: "+ getTodayHours(shopModel?.hours!!)

            Glide.with(thumbImage)
                .load(shopModel?.image)
                .into(thumbImage)
        }
    }

    private fun getTodayHours(hours: Hours): String?{
        val calendar: Calendar = Calendar.getInstance()
        val date: Date = calendar.time
        val day: String = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
        return when(day){
            "Sunday" -> hours.Sunday
            "Monday" -> hours.Monday
            "Tuesday" -> hours.Tuesday
            "Wednesday" -> hours.Wednesday
            "Thursday" -> hours.Thursday
            "Friday" -> hours.Friday
            "Saturday" -> hours.Saturday
            else -> hours.Sunday
        }
    }

    interface ShopListClickListener{
        fun onItemClick(shopModel: ShopModel)
    }
}