package com.example.serviceapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CentersActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var nMap: GoogleMap

    val dsl = LatLng(6.9342774, 79.8636039)
    val greasemonkey = LatLng(6.9213792, 79.8610405)
    val gsauto = LatLng(6.9315473, 79.8627144)
    val eautoparts = LatLng(6.9746304, 79.895234)

    private var locationArrayList: ArrayList<LatLng>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_centers)

        val map = supportFragmentManager
            .findFragmentById(R.id.myMap) as SupportMapFragment
        map.getMapAsync(this)

        locationArrayList = ArrayList()

        locationArrayList!!.add(dsl)
        locationArrayList!!.add(greasemonkey)
        locationArrayList!!.add(gsauto)
        locationArrayList!!.add(eautoparts)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        nMap = googleMap

        for (i in locationArrayList!!.indices){
            nMap.addMarker(MarkerOptions().position(locationArrayList!![i]).title("Marker"))
            nMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))
            nMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList!!.get(i)))

        }
    }
}