package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
//TODO: урок номер 7, сделать на следующую смену все из методички, отложить пока конвас, все по теме а там уже на что время хватит


class Lesson7 : Activity() {
    lateinit var myDraw : MyDrawable
    var run = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myDraw = MyDrawable(this)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        myDraw.setOnClickListener {  myDraw.onClick() }
        setContentView(myDraw)
        val myStep = 100f
        GlobalScope.launch {
            while (run) {
                delay((250).toLong())
                myDraw.changeYPos(myStep)
            }
        }
    }

    class MyDrawable(c: Context) : View(c) {
        var r = 100
        var g = 100
        var b = 100
        var xPos = 100f // для точки был Float
        var yPos = 100f
        var myP = Paint().apply {
            color = Color.BLACK
            strokeWidth = 100f
        }
        override fun onDraw(canvas: Canvas?) {
            canvas?.drawARGB(100,r,g,b)
            canvas?.drawCircle(xPos,yPos,100f, myP)
        }
        suspend fun changeXPos(x : Float){
            delay(1000)
            xPos = x
            invalidate()
        }
        fun changeYPos(y : Float){
            if(yPos > (this.height).toFloat()) yPos = 0f
            yPos += y
            invalidate()
        }
        fun onClick(){
            r = Random.nextInt(0,255)
            g = Random.nextInt(0,255)
            b = Random.nextInt(0,255)
            Toast.makeText(context,"red-$r \ngreen-$g \nblue-$b",Toast.LENGTH_SHORT).show()
            xPos += 100
            invalidate()
        }
    }
}