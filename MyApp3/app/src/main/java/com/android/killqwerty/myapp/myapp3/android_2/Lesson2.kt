package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import kotlinx.android.synthetic.main.android2_lesson2.*

class Lesson2 : Activity() {
//    override fun onMapReady(googleMap: GoogleMap?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        googleMap?.addMarker(MarkerOptions().position(LatLng(0.0, 0.0)).title("Marker"))
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson2)
        setMyButtons()
        setMap()
    }

    fun setMyButtons() {
        Toast.makeText(this,"${(andr2_les2_edText.text)}", Toast.LENGTH_LONG).show()
    }
    fun setMap(){
//       // val myMap = findViewById(R.id.andr2_les2_fragment_in_map_layout) as MapView
//        val myMap2 = fragmentManager.findFragmentById(R.id.andr2_les2_fragment_in_map_layout) as MapView
//        myMap2.getMapAsync(this)
     }
}