package com.example.serviceapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.serviceapplication.*

class PromotionAdapter (private val itemList:ArrayList<Promotion>) : RecyclerView.Adapter<PromotionAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.promoNum.text = currentItem.promoNo
        holder.itemName.text = currentItem.promoName
        holder.itemPrice.text = currentItem.promoPrice
        holder.itemDescription.text = currentItem.promoDecs



        val imgURL ="https://picsum.photos/600"
        Glide.with(holder.itemView.context).load(imgURL).circleCrop().into(holder.imgView)







    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val promoNum: TextView = itemView.findViewById(R.id.promono)
        val itemName : TextView = itemView.findViewById(R.id.tvfirstName)
        val itemPrice : TextView = itemView.findViewById(R.id.tvlastName)
        val itemDescription : TextView = itemView.findViewById(R.id.tvage)
        val imgView: ImageView = itemView.findViewById(R.id.img_promoView)


        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PromoRegister::class.java)
                itemView.context.startActivity(intent)

            }
        }



    }

}