package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R

import kotlinx.android.synthetic.main.android2_lesson2.*
import org.jetbrains.annotations.NotNull

class Lesson2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson2)
        setMyButtons()
        setMap()
    }

    fun setMyButtons() {
        Toast.makeText(this,"${(andr2_les2_edText.text)}", Toast.LENGTH_LONG).show()
// ключ AIzaSyDAINF9rLW_w2TgslOI-bf-dHuTiOjGRi4
    }
    fun setMap(){
        val fragment1 = Lesson2_map_fragment()
        val fragmentManager1 : FragmentManager = supportFragmentManager
        fragmentManager1.beginTransaction()
                .add(R.id.andr2_les2_fragment_in_map_layout,fragment1)
                .commit()
    }
}