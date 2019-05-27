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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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