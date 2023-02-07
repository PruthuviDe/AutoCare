package com.example.serviceapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity: AppCompatActivity() {

    private lateinit var txt_heading: TextView
    private lateinit var txt_signup: TextView
    private lateinit var img_signup: ImageView
    private lateinit var txt_email: TextView
    private lateinit var et_email: EditText
    private lateinit var txt_password: TextView
    private lateinit var et_password: EditText
    private lateinit var txt_confirmpassword: TextView
    private lateinit var et_confirmpassword: EditText
    private lateinit var btn_signup: Button
    private lateinit var txt_register: TextView
    private lateinit var txt_signin: TextView

    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)

        txt_heading = findViewById(R.id.txt_heading)
        txt_signup = findViewById(R.id.txt_signup)
        img_signup = findViewById(R.id.img_signup)
        txt_email = findViewById(R.id.txt_email)
        et_email = findViewById(R.id.et_email)
        txt_password = findViewById(R.id.txt_password)
        et_password = findViewById(R.id.et_password)
        txt_confirmpassword = findViewById(R.id.txt_confirmpassword)
        et_confirmpassword = findViewById(R.id.et_confirmpassword)
        btn_signup = findViewById(R.id.btn_signup)
        txt_register = findViewById(R.id.txt_register)
        txt_signin = findViewById(R.id.txt_signin)

        firebaseAuth = FirebaseAuth.getInstance()

        txt_signin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        btn_signup.setOnClickListener {

            val email = et_email.text.toString()
            val password = et_password.text.toString()
            val confirmPassword = et_confirmpassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this, DetailsActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Password is not matched", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed!!", Toast.LENGTH_SHORT).show()
            }
        }


    }

}