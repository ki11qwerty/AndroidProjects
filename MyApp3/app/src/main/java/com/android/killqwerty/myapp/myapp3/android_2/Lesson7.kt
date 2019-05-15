package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.Display
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
        GlobalScope.launch {
            while (run) {
                delay(10)
                myDraw.changeXPos(myDraw.myStep)
            }
        }
    }

    class MyDrawable(c: Context) : View(c) {
        var r = 100
        var g = 100
        var b = 100
        var xPos = (this.width / 2).toFloat()
        var yPos = -1240f
        var myRad = 100f
        var myStep = 10f
        var myP = Paint().apply {
            color = Color.WHITE
            strokeWidth = 1f
        }
        override fun onDraw(canvas: Canvas?) {
            if (yPos == -1240f) yPos = (height / 2).toFloat()
            canvas?.drawColor(Color.BLACK)
            canvas?.drawCircle(xPos, yPos,myRad, myP)
        }
        fun changeXPos(x : Float){
            if(xPos < -myRad) {
                xPos = (this.width + 250).toFloat()
                changeYPos(Random.nextInt(100,height).toFloat())
                changeMyRad()
            }
            xPos -= x
            invalidate()
        }
        fun changeYPos(y : Float){
            yPos = y
        }
        fun changeMyRad(){
            var rand = Random.nextInt(10,500).toFloat()
            myRad = rand
            changeMyStep((rand / 50) + 1 )
        }
        fun changeMyStep(speed : Float){
            myStep = speed
        }
        fun onClick(){
        }
    }

    override fun onDestroy() {
        run = false
        super.onDestroy()
    }
}