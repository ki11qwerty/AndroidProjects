package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R
import kotlin.random.Random

class Lesson7 : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        var myDraw = MyDrawable(this)
        myDraw.setOnClickListener { myDraw.onClick() }
        setContentView(myDraw)

    }
    class MyDrawable(c: Context) : View(c) {
        var r = 100
        var g = 100
        var b = 100
        var toast = Toast.makeText(context,"red-$r \ngreen-$g \nblue-$b",Toast.LENGTH_SHORT)
        override fun onDraw(canvas: Canvas?) {
            canvas?.drawARGB(100,r,g,b)
            toast.show()

        }
        fun onClick(){
            r = Random.nextInt(0,255)
            g = Random.nextInt(0,255)
            b = Random.nextInt(0,255)
            invalidate()
        }
    }
}