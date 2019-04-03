package com.android.killqwerty.myapp.myapp3.android_2


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.killqwerty.myapp.myapp3.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.CameraPosition
import kotlinx.android.synthetic.main.android2_lesson3_some_fragment.*


class Lesson3_Fragment : Fragment() , OnMapReadyCallback, View.OnClickListener{
    lateinit var myView : View
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.android2_lesson3_some_fragment,container,false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myMap = myView.findViewById(R.id.andr2_les2_my_mapView) as MapView
        myMap.onCreate(null)
        myMap.onResume()
        myMap.getMapAsync(this)
        setButtons()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(context)
        myGoogleMap = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        val settings = googleMap.uiSettings
        settings.isZoomControlsEnabled = true

        googleMap.addMarker(MarkerOptions()
                .position(volgogradMap)
                .title("Волгоград"))

        googleMap.addMarker(MarkerOptions()
                .position(steakHouseMap)
                .title("Steak House"))

        googleMap.addMarker(MarkerOptions()
                .position(homeMap)
                .title("Home"))

      changeCamera(volgogradMap, 10f)

    }
    fun setButtons(){
        andr2_les2_btn_work.setOnClickListener{ onClick(it) }
        andr2_les2_btn_home.setOnClickListener { onClick(it) }
        andr2_les2_volgograd.setOnClickListener { onClick(it) }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            andr2_les2_btn_home.id -> changeCamera(homeMap, 17f)
            andr2_les2_btn_work.id -> changeCamera(steakHouseMap, 17f)
            andr2_les2_volgograd.id -> changeCamera(volgogradMap, 10f)
        }
    }
    fun changeCamera(cordinats: LatLng, zoom: Float){
        val cameraPosition = CameraPosition.Builder()
                .target(cordinats)
                .zoom(zoom)
                .bearing(0f)
                .tilt(45f)
                .build()
        myGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
    companion object {
        lateinit var myMap: MapView
        lateinit var myGoogleMap: GoogleMap
        val volgogradMap = LatLng(48.7085007,44.5149151)
        val steakHouseMap = LatLng(48.7047823,44.5170374)
        val homeMap = LatLng(48.7587845,44.5057762)
    }
}