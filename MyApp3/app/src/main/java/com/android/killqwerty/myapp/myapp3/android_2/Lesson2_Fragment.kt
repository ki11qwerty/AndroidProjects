package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R
import com.google.android.gms.maps.*
import kotlinx.android.synthetic.main.android2_lesson2_some_fragment.*

class Lesson2_Fragment : Fragment() , OnMapReadyCallback, View.OnClickListener{
    lateinit var myView : View
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.android2_lesson2_some_fragment,container,false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myMap = myView.findViewById(R.id.andr2_les2_my_mapView) as MapView
        myMap.onCreate(null)
        myMap.onResume()
        myMap.getMapAsync(this)
        Toast.makeText(myView.context,"чпонь", Toast.LENGTH_LONG).show()
        setButtons()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(context)
        myGoogleMap = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        val settings = googleMap.uiSettings
        settings.isZoomControlsEnabled = true
        //TODO: короче закоменчю пока что это дело. завтра допилить все это дело с получением кординат, маркеры, ну и пробки можно добавить
//        settings.isMapToolbarEnabled = true
//        settings.isMyLocationButtonEnabled = true
//        if(ContextCompat.checkSelfPermission(myView.context,"android.permission.ACCESS_COARSE_LOCATION") == PackageManager.PERMISSION_GRANTED)
//            googleMap.isMyLocationEnabled = true
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun setButtons(){
     //   andr2_l2_btn_my_location.setOnClickListener{ onClick(it) }
    }
override fun onClick(view: View?) {
        when(view?.id){
         //   andr2_l2_btn_my_location.id -> Toast.makeText(myView.context,"чпонь", Toast.LENGTH_LONG).show()
        }
    }
    companion object {
        lateinit var myMap: MapView
        lateinit var myGoogleMap: GoogleMap
    }
}