package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.killqwerty.myapp.myapp3.R
import com.google.android.gms.maps.*

class Lesson2_Fragment : Fragment() , OnMapReadyCallback{
    lateinit var myView : View
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.android2_lesson2_some_fragment,container,false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val myMap: MapView? = myView.findViewById(R.id.andr2_les2_my_mapView) as MapView
        if (myMap !=null){
            myMap.onCreate(null)
            myMap.onResume()
            myMap.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(context)
        var myGoogleMap = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}