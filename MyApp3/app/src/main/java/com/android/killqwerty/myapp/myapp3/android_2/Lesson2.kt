package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.WindowManager
import com.android.killqwerty.myapp.myapp3.R

class Lesson2 : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.

        setContentView(R.layout.android2_lesson2)
        val fragment = Lesson2_Fragment()
        val fragManager = supportFragmentManager
            fragManager.beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit()
        }
}