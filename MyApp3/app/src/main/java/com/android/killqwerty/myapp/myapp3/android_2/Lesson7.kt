package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R
import kotlinx.coroutines.*
import java.util.zip.Inflater
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random
//TODO: урок номер 7, сделать на следующую смену все из методички, отложить пока конвас, все по теме а там уже на что время хватит
//TODO: завтра переделать это убожище, что то я не уловил видимо)

class Lesson7 : Activity() {
    lateinit var myDraw : MyDrawable
    //lateinit var myStar1 : StarDrawable
    var run = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myDraw = MyDrawable(this)
      //  myStar1 = StarDrawable(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        myDraw.setOnClickListener {  myDraw.onClick() }
        setContentView(myDraw)
        //myDraw.star.flyAway()
        GlobalScope.launch {
            while (run) {
                delay(10)
                myDraw.changeXPos(myDraw.myStep)
            }
        }

//        addContentView(myStar1, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
//        myStar1.flyAway()
    }

    override fun onResume() {
       // myStar1.flyAway()
        super.onResume()
    }

    override fun onPause() {
    //    myStar1.stop()
        super.onPause()
    }

    class StarDrawable(c: Context) : View(c){

        var isRunning = false
        var myRandom : Random = Random
        val standartX = 1000f
        var xPos = standartX
        var yPos = myRandom.nextInt(0,1080).toFloat()// (0,this.height).toFloat()
        var myRad = myRandom.nextInt(10,500).toFloat()
        var speed = myRad / 10
        val myPaint = Paint().apply {
            color = Color.WHITE
            strokeWidth = 1f
        }

        fun reCreate(){
            xPos = standartX
            yPos = myRandom.nextInt(0,1080).toFloat()// (0,this.height).toFloat()
            myRad = myRandom.nextInt(10,500).toFloat()
            speed = myRad / 10
            invalidate()

        }
        fun flyAway(){
            myCoroutine = GlobalScope.launch {
                isRunning = true
                while (isRunning) {
                    delay(10)
                    xPos -= speed
                    if (xPos < -myRad)
                        reCreate()
                    invalidate()
                }
            }.start()
        }
        fun stop(){
            isRunning = false
        }

        override fun onDraw(canvas: Canvas?) {
            canvas?.drawCircle(xPos,yPos,myRad,myPaint)
            super.onDraw(canvas)
        }
        companion object{
            var myCoroutine = false
        }

    }

    class MyDrawable(c: Context) : View(c) {
        var xPos = (this.width / 2).toFloat()
        var yPos = -1240f
        var myRad = 100f
        var myStep = 10f
        var myStr = "сбоку пососи"
        var myP = Paint().apply {
            color = Color.WHITE
            strokeWidth = 1f
        }
        var myP2 = Paint().apply {
            color = Color.BLACK
            strokeWidth = 1f
            textSize = myRad / 2

        }
        var myP3 = Paint().apply {
            color = Color.GREEN
            strokeWidth = 5f
            textSize = 75f
        }
        override fun onDraw(canvas: Canvas?) {
            if (yPos == -1240f) yPos = (height / 2).toFloat()
            canvas?.drawColor(Color.BLACK)
            canvas?.drawCircle(xPos, yPos,myRad, myP)
            canvas?.drawText(myStr,(xPos - myRad * 0.9f),(yPos),myP2)
            canvas?.drawText("скорость: $myStep px/10нано",10f,height - 100f,myP3)
        }
        fun changeXPos(x : Float){
            if(xPos < -myRad) {
                xPos = (this.width + 250).toFloat()
                changeYPos(Random.nextInt(100,height).toFloat())
                changeMyRad()
                myP2.textSize = myRad / 3.5f// удалить потом
            }
            xPos -= x
            invalidate()
        }
        fun changeYPos(y : Float){
            yPos = y
        }
        fun changeMyRad(){
            val rand = Random.nextInt(10,500).toFloat()
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