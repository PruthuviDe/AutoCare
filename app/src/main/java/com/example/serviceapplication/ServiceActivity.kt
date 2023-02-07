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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serviceapplication.model.ServiceModel

class ServiceActivity : AppCompatActivity() {

    private lateinit var service_RecyclerView: RecyclerView
    private var fdb : FirebaseDB = FirebaseDB()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

//        FirebaseDB().setDataServiceDB()

        service_RecyclerView = findViewById<RecyclerView>(R.id.service_RecyclerView)
        service_RecyclerView.layoutManager = LinearLayoutManager(this)
        service_RecyclerView.setHasFixedSize(true)

        fdb.getServiceData(firebaseCallback = object : FirebaseCallback {
            override fun onCallback() {
                Log.w("Datax", fdb.service_list.toString())
                service_RecyclerView.adapter = ServiceAdapter(fdb.service_list)
            }
        })
    }

    inner class ServiceAdapter(private val service_list : ArrayList<ServiceModel>) :
        RecyclerView.Adapter<ServiceAdapter.ServiceHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
            val itemView  = LayoutInflater.from(parent.context).inflate(
                R.layout.service_item, parent, false
            )
            return ServiceHolder(itemView)
        }

        override fun onBindViewHolder(holder: ServiceHolder, position: Int) {
            val current_item = service_list[position]
            holder.service_name.text= current_item.serviceName
            holder.service_price.text = current_item.price
            Log.w("Included", current_item.included.toString())
            if(current_item.included!!.size != 0)  holder.lst_include.adapter = ArrayAdapter(holder.itemView.context,android.R.layout.simple_list_item_1, current_item.included!!)
            holder.itemView.setOnClickListener {
                val intent = Intent(this@ServiceActivity, ServiceViewActivity::class.java)
                intent.putExtra("Data", current_item)
                startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return service_list.size
        }

        inner class ServiceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val service_name : TextView = itemView.findViewById(R.id.txt_name)
            val service_price : TextView = itemView.findViewById(R.id.txt_price)
            val lst_include : ListView = itemView.findViewById(R.id.lst_include)
        }
    }
}