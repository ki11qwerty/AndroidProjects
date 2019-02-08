package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R
import com.android.killqwerty.myapp.myapp3.R.*
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.SupportMapFragment

import kotlinx.android.synthetic.main.android2_lesson2.*

class Lesson2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.android2_lesson2)
        setMyButtons()
        setMap()
    }
    //не пойму че к чему, завтра додумаю не пошло...

    fun setMyButtons() {
        Toast.makeText(this,"${(andr2_les2_edText.text)}", Toast.LENGTH_LONG).show()
// ключ AIzaSyDAINF9rLW_w2TgslOI-bf-dHuTiOjGRi4
    }
    fun setMap(){
//        val fragment1 = Lesson2_map_fragment()
//        val mapManager : MapFragment? = MapFragment()
//        mapManager.
//                .add(R.id.andr2_les2_fragment,fragment1) // непонятно чет нифига... завтра допилю
//                .commit()
    }
}