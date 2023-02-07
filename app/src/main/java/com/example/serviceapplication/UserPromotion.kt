package com.example.serviceapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serviceapplication.adapter.PromotionAdapter
import com.google.firebase.database.*

class UserPromotion : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var promoArrayList: ArrayList<Promotion>
    private lateinit var userRecyclerview : RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_promotion)


        userRecyclerview = findViewById(R.id.rcy_promo)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)


        promoArrayList = arrayListOf<Promotion>()
        getUserData()


    }


    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("Promotion")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Promotion::class.java)
                        promoArrayList.add(user!!)
                    }
                    userRecyclerview.adapter = PromotionAdapter(promoArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }
        })





    }




}
