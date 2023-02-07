package com.example.serviceapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.serviceapplication.databinding.ActivityDetailsBinding
import com.example.serviceapplication.model.UserModel

class DetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener{
            if (binding.txtUsername.text.toString().length == 0 )
                Toast.makeText(this,"Please Fill The Username", Toast.LENGTH_SHORT).show()
            else if (binding.txtFirstname.text.length == 0)
                Toast.makeText(this,"Please Fill Your First Name", Toast.LENGTH_SHORT).show()
            else if (binding.txtLastname.text.length == 0)
                Toast.makeText(this, "Please Fill Your Last Name", Toast.LENGTH_SHORT).show()
            else if (binding.txtAddress.text.length == 0)
                Toast.makeText(this, "Please Fill Your Address", Toast.LENGTH_SHORT).show()
            else if (binding.txtTel.text.length == 0)
                Toast.makeText(this, "Please Fill Your Phone Number", Toast.LENGTH_SHORT).show()
            else {
                var user = UserModel()
                user.username = binding.txtUsername.text.toString()
                user.firstname = binding.txtFirstname.text.toString()
                user.lastname = binding.txtLastname.text.toString()
                user.address = binding.txtAddress.getText().toString()
                user.tel = binding.txtTel.getText().toString().toInt()
                SQLiteDB(this).insertData(user)
                FirebaseDB().setDataUserDB(user)

//                Log.w("Data", SQLiteDB(applicationContext).readData().toString())
                startActivity(Intent(this,DashBoard::class.java))
            }
        }
    }
}