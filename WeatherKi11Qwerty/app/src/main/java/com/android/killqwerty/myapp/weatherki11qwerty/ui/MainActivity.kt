package com.android.killqwerty.myapp.weatherki11qwerty.ui

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import com.android.killqwerty.myapp.weatherki11qwerty.R

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
    }
}