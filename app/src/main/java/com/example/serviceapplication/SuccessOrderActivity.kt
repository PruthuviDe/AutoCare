package com.example.serviceapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.serviceapplication.R
import com.example.serviceapplication.model.ShopModel
import kotlinx.android.synthetic.main.activity_success_order.*

class SuccessOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_order)

        val btn_done = findViewById<TextView>(R.id.buttonDone)


        btn_done.setOnClickListener {
            val intent = Intent(this, DashBoard::class.java)
            startActivity(intent)
        }

    }
}