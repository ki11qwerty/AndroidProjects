package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.killqwerty.myapp.myapp3.R
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.android2_lesson2_fragment.*

class Lesson2_map_fragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.android2_lesson2_fragment, container,false)
    }
}