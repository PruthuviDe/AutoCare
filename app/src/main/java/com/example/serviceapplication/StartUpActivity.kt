package com.example.serviceapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartUpActivity: AppCompatActivity() {

    private lateinit var txt_heading: TextView
    private lateinit var txt_subheading: TextView
    private lateinit var img_main: ImageView
    private lateinit var txt_main_description: TextView
    private lateinit var btn_start: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        txt_heading = findViewById(R.id.txt_heading)
        txt_subheading = findViewById(R.id.txt_subheading)
        img_main = findViewById(R.id.img_main)
        txt_main_description = findViewById(R.id.txt_main_description)
        btn_start = findViewById(R.id.btn_start)

        btn_start.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}