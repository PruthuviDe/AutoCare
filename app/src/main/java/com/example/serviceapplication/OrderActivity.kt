package com.example.serviceapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serviceapplication.model.OrderModel
import com.example.serviceapplication.model.UserModel
import kotlinx.android.synthetic.main.activity_order.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class OrderActivity : AppCompatActivity() {

    private lateinit var service_RecyclerView: RecyclerView
    private var fdb : FirebaseDB = FirebaseDB()
    private lateinit var users : ArrayList<UserModel>
    private lateinit var user : UserModel
    private var order_list : ArrayList<OrderModel> = arrayListOf<OrderModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        this.users = SQLiteDB(applicationContext).readData()
        user = users.get(this.users.size-1)
        service_RecyclerView = findViewById<RecyclerView>(R.id.order_RecyclerView)
        service_RecyclerView.layoutManager = LinearLayoutManager(this)
        service_RecyclerView.setHasFixedSize(true)

        fdb.getOrderData(firebaseCallback = object : FirebaseCallback {
            override fun onCallback() {
//                Log.w("Datax", fdb.order_list.toString())
                for(order in fdb.order_list){
                    if(order.userName == this@OrderActivity.user.username) {
                        this@OrderActivity.order_list.add(order)
                    }
                }
                if(this@OrderActivity.order_list.isEmpty()){
                    findViewById<TextView>(R.id.txt_status).setText("No Order Placed Yet")
                }
                else{
                    order_RecyclerView.adapter = OrderAdapter(this@OrderActivity.order_list)
                }
            }
        })

    }

    inner class OrderAdapter(private val order_list : ArrayList<OrderModel>) :
        RecyclerView.Adapter<OrderAdapter.OrderHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
            val itemView  = LayoutInflater.from(parent.context).inflate(
                R.layout.order_item, parent, false
            )
            return OrderHolder(itemView)
        }

        override fun onBindViewHolder(holder: OrderHolder, position: Int) {
            val sdf = SimpleDateFormat("dd/M/yyyy")
            val current_item = order_list[position]
            holder.order_id.text = current_item.orderId
            var x = SimpleDateFormat("dd-MM-yyyy", Locale.UK).format(current_item.datetime)
            holder.date.text = x.toString()
            holder.service.text = current_item.serviceModel?.serviceName.toString()
            holder.vehicle_no.text = current_item.vehicleNumber.toString()
            holder.total.text = current_item.serviceModel?.price
        }

        override fun getItemCount(): Int {
            return order_list.size
        }

        inner class OrderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val order_id : TextView = itemView.findViewById(R.id.txt_oid)
            val date : TextView = itemView.findViewById(R.id.txt_date)
            val service : TextView = itemView.findViewById(R.id.txt_service)
            val vehicle_no : TextView = itemView.findViewById(R.id.txt_vnum)
            val total : TextView = itemView.findViewById(R.id.txt_total)
        }
    }
}