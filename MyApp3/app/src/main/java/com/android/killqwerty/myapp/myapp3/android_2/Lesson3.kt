package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.WindowManager
import com.android.killqwerty.myapp.myapp3.R

class Lesson3 : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.android2_lesson3)
        val fragment = Lesson3_Fragment()
        val fragManager = supportFragmentManager
            fragManager.beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit()
        }
}