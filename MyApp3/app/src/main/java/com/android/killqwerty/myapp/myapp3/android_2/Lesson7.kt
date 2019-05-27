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
    var w = windowManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyDraw(this))
    }
}
class MyDraw(c: Context) : View(c){
    val mX = 500f
    val mY = 500f
    val mRad = 300f
    var mHeight = this.height.toFloat()
    val mArr = floatArrayOf(100f,100f,100f,mHeight+1000f)// arrayOf(10f,10f,20f,20f,30f,30f,40f,40f,50f,50f)
    val mPaint : Paint = Paint().apply {
        color = Color.WHITE
        strokeWidth = 5f
    }
    val mPaint2 = Paint().apply {
        color = Color.GREEN
        strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(Color.BLACK)
        canvas?.drawCircle(mX,mY,mRad,mPaint)
        canvas?.drawLines(mArr,mPaint2)
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