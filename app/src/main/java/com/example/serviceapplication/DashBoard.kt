package com.example.serviceapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoard : AppCompatActivity() {

    private lateinit var txt_footer: TextView
    private lateinit var img_btn_order: ImageButton
    private lateinit var img_btn_location: ImageButton
    private lateinit var img_btn_profile: ImageButton
    private lateinit var img_btn_logout: ImageButton
    private lateinit var txt_dashboard: TextView
    private lateinit var txt_subheading: TextView
    private lateinit var btn_services: Button
    private lateinit var btn_accessories: Button
    private lateinit var btn_QRscan: Button
    private lateinit var btnHelp: Button
    private lateinit var img_btn_feedback: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        txt_footer = findViewById(R.id.txt_footer)
        img_btn_order = findViewById(R.id.img_btn_order)
        img_btn_location = findViewById(R.id.img_btn_location)
        img_btn_profile = findViewById(R.id.img_btn_profile)
        img_btn_logout = findViewById(R.id.img_btn_logout)
//        txt_dashboard = findViewById(R.id.txt_dashboard)
//        txt_subheading = findViewById(R.id.txt_subheading)
        btn_services = findViewById(R.id.btn_services)
        btn_accessories = findViewById(R.id.btn_accessories)
        btn_QRscan = findViewById(R.id.btn_qr)
        btnHelp = findViewById(R.id.btn_help)
        img_btn_feedback = findViewById(R.id.img_btn_feedback)

        btn_accessories.setOnClickListener {
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }

        img_btn_logout.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        img_btn_location.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        img_btn_profile.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btn_services.setOnClickListener{
            startActivity(Intent(this, ServiceActivity::class.java))
        }

        img_btn_order.setOnClickListener{
            startActivity(Intent(this, OrderActivity::class.java))
        }
        btn_promotion.setOnClickListener{
            startActivity(Intent(this, UserPromotion::class.java))
        }
        btn_QRscan.setOnClickListener{
            startActivity(Intent(this, QrScanCode::class.java))
        }
        btnHelp.setOnClickListener{
            startActivity(Intent(this, FindLocation::class.java))
        }

        img_btn_feedback.setOnClickListener{
            startActivity(Intent(this, FeedbackActivity::class.java))
        }
    }
}