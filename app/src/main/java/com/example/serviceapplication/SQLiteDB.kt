package com.example.serviceapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.serviceapplication.model.UserModel
import com.example.serviceapplication.model.VehicleModel

class SQLiteDB (context: Context) : SQLiteOpenHelper(context,"APP", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE USER(UID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, FIRSTNAME TEXT, LASTNAME TEXT, ADDRESS TEXT, TEL INTEGER)")
        db?.execSQL("CREATE TABLE VEHICLE(VID INTEGER PRIMARY KEY AUTOINCREMENT, VEHICLE_NO TEXT, VEHICLE_TYPE TEXT, CHASSY_NO TEXT, USERNAME TEXT)")
//        db?.execSQL("INSERT INTO VEHICLE(VEHICLE_NO, VEHICLE_TYPE, CHASSY_NO, USERNAME) VALUES ('PP-1122', 'Car', 'slkdje423dfwe', 'xoxo')")
//        db?.execSQL("INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME) VALUES ('kavinda2003', 'Kavinda', 'Disanayake')")
//        db?.execSQL("INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME) VALUES ('janith3003', 'Janith', 'Disanayake')")
    }

    fun insertData(obj : UserModel) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put("USERNAME", obj.username)
        cv.put("FIRSTNAME", obj.firstname)
        cv.put("LASTNAME", obj.lastname)
        cv.put("ADDRESS", obj.address)
        cv.put("TEL", obj.tel)

        db.insert("USER",null, cv)

    }

    fun readData(): ArrayList<UserModel> {
        val db = this.writableDatabase
        val query = "SELECT * FROM USER"
        val user_list = ArrayList<UserModel>()
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var user = UserModel()
                user.username = result.getString(1)
                user.firstname = result.getString(2)
                user.lastname = result.getString(3)
                user.address = result.getString(4)
                user.tel = result.getInt(5)
                user.vehicles = readVehicleData(user.username!!)
                user_list.add(user)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return user_list
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertVehicleData(username: String, obj: VehicleModel) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put("VEHICLE_NO", obj.regnumber)
        cv.put("VEHICLE_TYPE", obj.type)
        cv.put("CHASSY_NO", obj.chassynumber)
        cv.put("USERNAME", username)

        db.insert("VEHICLE",null, cv)
    }

    fun readVehicleData(username : String): ArrayList<VehicleModel> {
        val db = this.writableDatabase
        val query = "SELECT * FROM VEHICLE"
        val vehicle_list = ArrayList<VehicleModel>()
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                if(result.getString(4)==username){
                    var vehicle = VehicleModel()
                    vehicle.regnumber = result.getString(1)
                    vehicle.type = result.getString(2)
                    vehicle.chassynumber = result.getString(3)
                    vehicle_list.add(vehicle)
                }
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return vehicle_list
    }

    fun readVehicleNumbers() : ArrayList<String> {
        val db = this.writableDatabase
        val query = "SELECT * FROM VEHICLE"
        val vehicle_list = ArrayList<String>()
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                vehicle_list.add(result.getString(1))
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return vehicle_list
    }

    fun readVehicleNumers(username : String): ArrayList<String> {
        val db = this.writableDatabase
        val query = "SELECT * FROM VEHICLE"
        val vehicle_list = ArrayList<String>()
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                if(result.getString(4)==username){
                    vehicle_list.add(result.getString(1))
                }
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return vehicle_list
    }

    fun truncateTables() {
        val db = this.writableDatabase
    }
}