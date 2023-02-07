package com.example.serviceapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class FeedbackActivity : AppCompatActivity() {

    private lateinit var btn_send: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        btn_send = findViewById(R.id.btn_send)
        btn_send.setOnClickListener {
            Toast.makeText(this, "Your Feedback Sent",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashBoard::class.java))
        }

    }


}