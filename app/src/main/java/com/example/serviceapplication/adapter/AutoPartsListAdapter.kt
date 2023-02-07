package com.example.serviceapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.serviceapplication.R
import com.example.serviceapplication.model.AutoParts
import kotlinx.android.synthetic.main.autoparts_list_row.view.*

class AutoPartsListAdapter(val autoPartsList: List<AutoParts?>?, val clickListener: AutoPartsListClickListener): RecyclerView.Adapter<AutoPartsListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):AutoPartsListAdapter.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.autoparts_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder:AutoPartsListAdapter.MyViewHolder, position: Int){
        holder.bind(autoPartsList?.get(position)!!)
    }

    override fun getItemCount(): Int{
        return if(autoPartsList==null) return 0 else autoPartsList.size
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var thumbImage: ImageView = view.thumbImage
        val autopartName: TextView = view.autopartName
        val autopartPrice: TextView = view.autopartPrice
        val addToCartButton: TextView = view.addToCartButton
        val addMoreLayout: LinearLayout = view.addMoreLayout
        val imageMinus: ImageView = view.imageMinus
        val tvCount: TextView = view.tvCount
        val imageAddOne: ImageView = view.imageAddOne

        fun bind(autoParts: AutoParts){
            autopartName.text = autoParts?.name
            autopartPrice.text = "Price: $ ${autoParts?.price}"
            addToCartButton.setOnClickListener{
                autoParts?.totalInCart = 1
                clickListener.addtoCartClickListener(autoParts)
                addMoreLayout?.visibility = View.VISIBLE
                addToCartButton.visibility = View.GONE
                tvCount.text = autoParts?.totalInCart.toString()
            }
            imageMinus.setOnClickListener{
                var total: Int = autoParts.totalInCart
                total--
                if (total>0){
                    autoParts?.totalInCart = total
                    clickListener.updateCartClickListener(autoParts)
                    tvCount.text = autoParts?.totalInCart.toString()
                }else{
                    autoParts.totalInCart = total
                    clickListener.removeFromCartClickListener(autoParts)
                    addMoreLayout.visibility = View.GONE
                    addToCartButton.visibility = View.VISIBLE
                }

            }
            imageAddOne.setOnClickListener {
                var total: Int = autoParts.totalInCart
                total++
                if (total<=10){
                    autoParts.totalInCart = total
                    clickListener.updateCartClickListener(autoParts)
                    tvCount.text = total.toString()
                }
            }

            Glide.with(thumbImage)
                .load(autoParts?.url)
                .into(thumbImage)
        }
    }

    interface AutoPartsListClickListener{
        fun addtoCartClickListener(autoParts: AutoParts)
        fun updateCartClickListener(autoParts: AutoParts)
        fun removeFromCartClickListener(autoParts: AutoParts)
    }
}