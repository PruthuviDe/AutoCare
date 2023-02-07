package com.example.serviceapplication

import android.util.Log
import com.example.serviceapplication.model.OrderModel
import com.example.serviceapplication.model.ServiceModel
import com.example.serviceapplication.model.UserModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class FirebaseDB {

    var database : FirebaseDatabase = FirebaseDatabase.getInstance()

    lateinit var dbref : DatabaseReference
    lateinit var service_list : ArrayList<ServiceModel>
    lateinit var order_list : ArrayList<OrderModel>
    lateinit var user : UserModel

    fun setDataUserDB(user : UserModel) {
        var user_database : DatabaseReference = database.getReference("User")
        user_database.child(user.username.toString()).setValue(user).addOnSuccessListener {
            Log.w("Done", "Yes")
        }
    }

    fun setDataServiceDB() {
        var service = ServiceModel("Basic Service", arrayListOf("A","B","C"), arrayListOf("D","E","F"),"5000", arrayListOf("G","H","I"))
        var service2 = ServiceModel("Premium Service", arrayListOf("P","Q","R"), arrayListOf("S","T","U"),"10000", arrayListOf("V","W","X"))
        var service3 = ServiceModel("Normal Service", arrayListOf("a","b","c"), arrayListOf("d","e","f"),"10000", arrayListOf("g","h","i"))

        var services = ArrayList<ServiceModel>()
        services.add(service)
        services.add(service2)
        services.add(service3)

        var service_database : DatabaseReference = database.getReference("Service")

        service_database.setValue(services).addOnSuccessListener {
            Log.w("Done", "Yes")
        }
    }

    fun setDataOrderDB(order : OrderModel) {
        var order_database : DatabaseReference = database.getReference("Order")

        order.orderId?.let {
            order_database.child(it).setValue(order).addOnSuccessListener {
                Log.w("Done", "Yes")
            }
        }
    }

    fun getUserData(firebaseCallback: FirebaseCallback, username : String) {
        dbref = FirebaseDatabase.getInstance().getReference("User").child(username)
        dbref.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    this@FirebaseDB.user = snapshot.getValue(UserModel::class.java)!!
                    firebaseCallback.onCallback()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getServiceData(firebaseCallback : FirebaseCallback) {

        dbref = FirebaseDatabase.getInstance().getReference("Service")
        service_list = arrayListOf<ServiceModel>()
        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    for (serviceSnapshot in snapshot.children) {
                        val data = serviceSnapshot.getValue(ServiceModel::class.java)
                        service_list.add(data!!)
                    }
                    Log.w("Data", service_list.toString())
                    firebaseCallback.onCallback()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getOrderData(firebaseCallback : FirebaseCallback) {
        dbref = FirebaseDatabase.getInstance().getReference("Order")
        order_list = arrayListOf<OrderModel>()
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (orderSnapshot in snapshot.children) {
                        val data = orderSnapshot.getValue(OrderModel::class.java)
                        order_list.add(data!!)
                    }
                    firebaseCallback.onCallback()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}