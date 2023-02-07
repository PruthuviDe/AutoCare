package com.example.serviceapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import com.example.serviceapplication.model.OrderModel
import com.example.serviceapplication.model.ServiceModel
import kotlinx.android.synthetic.main.activity_booking.*
import java.text.SimpleDateFormat
import java.util.*

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        var data = intent.getSerializableExtra("Data") as? ServiceModel
        var order = OrderModel()
        var txt_username : EditText = findViewById(R.id.txt_username)
        var txt_vnum : EditText = findViewById(R.id.txt_vnum)
        var cal_date : CalendarView = findViewById(R.id.calendarView)
        var date : Calendar? = null

        cal_date.setOnDateChangeListener { view, year, month, day ->
            val calender : Calendar = Calendar.getInstance()
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, month)
            calender.set(Calendar.DAY_OF_MONTH, day)
            date = calender
//            var x = SimpleDateFormat("dd-MM-yyyy", Locale.UK).format(calender.time)
//            Log.w("Date", x.toString())
        }

        var users = SQLiteDB(applicationContext).readData()
        var user = users.get(users.size-1)
        txt_username.setText(user.username)

        findViewById<Button>(R.id.btn_submit).setOnClickListener{
            val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            order.orderId = List(20) { alphabet.random() }.joinToString("")
            order.userName = user.username
            order.vehicleNumber = txt_vnum.text.toString()
            order.serviceModel = data
            if (date!=null) order.datetime  = date!!.time
            else order.datetime = Calendar.getInstance().time
            Log.w("Obj-Date",order.datetime.toString())
            FirebaseDB().setDataOrderDB(order)
            Toast.makeText(applicationContext, "Order is placed",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashBoard::class.java))
        }

    }
}