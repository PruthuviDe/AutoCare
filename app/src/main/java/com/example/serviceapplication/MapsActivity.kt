package com.example.serviceapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.serviceapplication.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in dsl and move the camera
        val dsl = LatLng(6.9342774, 79.8636039)
        mMap.addMarker(MarkerOptions().position(dsl).title("Marker in DSL"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dsl))

        // Add a marker in greasemonkey and move the camera
        val greasemonkey = LatLng(6.9213792, 79.8610405)
        mMap.addMarker(MarkerOptions().position(greasemonkey).title("Marker in Grease Monkey"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(greasemonkey))

        // Add a marker in gsauto and move the camera
        val gsauto = LatLng(6.9315473, 79.8627144)
        mMap.addMarker(MarkerOptions().position(gsauto).title("Marker in GS Auto"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gsauto))

        // Add a marker in eautoparts and move the camera
        val eautoparts = LatLng(6.9746304, 79.895234)
        mMap.addMarker(MarkerOptions().position(eautoparts).title("Marker in E Auto parts"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(eautoparts))
    }
}