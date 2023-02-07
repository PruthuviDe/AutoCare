package com.example.serviceapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.serviceapplication.model.ServiceModel

class ServiceViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_view)

        var data = intent.getSerializableExtra("Data") as? ServiceModel

        if (data != null) {
            val txt_name: TextView = findViewById(R.id.txt_name)
            val txt_price: TextView = findViewById(R.id.txt_price)
            val lst_include: ListView = findViewById(R.id.lst_include)
            val lst_notinclude: ListView = findViewById(R.id.lst_notinclude)
            val button: Button = findViewById(R.id.button)

            txt_name.setText(data?.serviceName.toString())
            txt_price.setText("Price: " + data?.price.toString())

            lst_include.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, data.included!!)

            lst_notinclude.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, data.notincluded!!)

            button.setOnClickListener {
                val intent = Intent(this, BookingActivity::class.java)
                intent.putExtra("Data", data)
                startActivity(intent)
            }

        }
    }
}