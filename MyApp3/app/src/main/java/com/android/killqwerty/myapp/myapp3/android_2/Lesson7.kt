package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R

// TODO: осталась анимация на дом

class Lesson7 : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson7)
        var display : Display = windowManager.defaultDisplay
        var mPoint = Point()
        display.getSize(mPoint)
        xDisplay = mPoint.x
        yDisplay = mPoint.y
       findViewById<ImageButton>(R.id.a2_l7_ib_canvas).setOnClickListener{setContentView(MyDraw(this))}
        findViewById<ImageButton>(R.id.a2_l7_ib_animation).setOnClickListener{
            setContentView(R.layout.android2_lesson7_animation)
            animateThis()
        }

    }
    fun animateThis(){
        val myImageView = findViewById<ImageView>(R.id.a2l7_iv)
        myImageView.setBackgroundResource(R.drawable.anim)
        val animation = myImageView.background as AnimationDrawable
        animation.start()
    }
    companion object{
        var xDisplay: Int = -1
        var yDisplay: Int = -1
    }
}
class MyDraw(c: Context) : View(c){
    var xDisplay: Float = Lesson7.xDisplay.toFloat()
    var yDisplay: Float = Lesson7.yDisplay.toFloat()
    val mX = (xDisplay / 2)
    val mY = (yDisplay / 2)
    val mRad = 450f
    val mArr = floatArrayOf(100f, 100f, 100f, yDisplay - 100f,
            100f, 100f, xDisplay - 100f, 100f,
            xDisplay - 100f, 100f, xDisplay - 100, yDisplay - 100f,
            100f, yDisplay - 100f, xDisplay - 100, yDisplay - 100f,
            100f, 100f, xDisplay - 100f, yDisplay - 100f,
            100f, yDisplay - 100f, xDisplay - 100f, 100f)

    val mPaint = Paint().apply {
        color = Color.GREEN
        strokeWidth = 3.3f
    }
    val mPaint2 = Paint().apply {
        color = Color.BLACK
        strokeWidth = 3.3f
    }
    var mPaint3 = Paint().apply {
        color = Color.RED
        strokeWidth = 3.3f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(Color.BLACK)
        canvas?.drawLines(mArr,mPaint)
        canvas?.drawCircle(mX,mY,mRad,mPaint)
        for(x in 10..mRad.toInt() step 10){
            canvas?.drawCircle(mX,mY,(mRad - x) ,mPaint2)
            canvas?.drawCircle(mX,mY,mRad - x - 3.3f,mPaint)
            canvas?.drawCircle(mX,mY,mRad - x - 6.6f,mPaint3)
            Log.d("PIZDEC","$x")
        }
        super.onDraw(canvas)
    }


}
//Домашнее задание
// Разобрать все примеры из урока
// Реализовать компонент UI который
//может рисовать на экране одну из
//простых геометрических фигур
//(окружность, квадрат, треугольник),
//причем фигуру можно задать в файле
//разметки
// Добавить несколько типов анимации