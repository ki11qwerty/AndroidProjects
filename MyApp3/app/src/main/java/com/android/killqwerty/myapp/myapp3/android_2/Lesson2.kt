package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import kotlinx.android.synthetic.main.android2_lesson2.*

class Lesson2 : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson2)
        val fragment = Lesson2_Fragment()
        setMyButtons()
        val fragManager = supportFragmentManager
            fragManager.beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit()
        }

    fun setMyButtons() {
        Toast.makeText(this,"${(andr2_les2_edText.text)}", Toast.LENGTH_LONG).show()
    }
}