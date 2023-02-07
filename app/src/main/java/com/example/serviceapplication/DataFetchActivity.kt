package com.example.serviceapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order.*

class DataFetchActivity : AppCompatActivity() {

    private var fdb : FirebaseDB = FirebaseDB()
    private var sqlitedb : SQLiteDB = SQLiteDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_fetch)


        val view = layoutInflater.inflate(R.layout.layout_fetch, null)
        val builder = android.app. AlertDialog.Builder(this).setView(view)
        val username = view.findViewById<EditText>(R.id.txt_username)

        val alertDialog = builder.create()
        builder.setNegativeButton("Done"){ dialog, which->

            fdb.getUserData(firebaseCallback = object : FirebaseCallback {
                override fun onCallback() {
                    Log.w("User", fdb.user.toString())
                    try {
                        sqlitedb.insertData(fdb.user)
                        if (fdb.user.vehicles!=null && sqlitedb.readVehicleNumbers().size != 0) {
                            for (vehicle in fdb.user.vehicles!!) {
                                for(current_vehicle in sqlitedb.readVehicleNumers(fdb.user.username!!)){
                                    if(vehicle.regnumber != current_vehicle) sqlitedb.insertVehicleData(fdb.user.username!!, vehicle)
                                }

                            }
                        }
                        else if(sqlitedb.readVehicleNumbers().size == 0) {
                            for (vehicle in fdb.user.vehicles!!) {
                                sqlitedb.insertVehicleData(fdb.user.username!!, vehicle)
                            }
                        }
                    }
                    catch (e : Exception){
                        Toast.makeText(applicationContext, "Sign is not Successfull", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@DataFetchActivity, SignInActivity::class.java))
                    }
//                    Log.w("User", fdb.user.toString())
                    startActivity(Intent(this@DataFetchActivity, DashBoard::class.java))
                }
            }, username.text.toString())

        }
        builder.setPositiveButton("Cancel") { dialog, which->
//            Toast.makeText(applicationContext, "Cancel", Toast.LENGTH_SHORT).show()
        }
        builder.setOnDismissListener {
            if (username.text.length == 0){
                Toast.makeText(applicationContext, "Sign is not Successfull", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }
        builder.show()
    }
}