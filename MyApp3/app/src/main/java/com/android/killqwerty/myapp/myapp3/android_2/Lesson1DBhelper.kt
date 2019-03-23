package com.android.killqwerty.myapp.myapp3.android_2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Lesson1DBhelper(context: Context?, name: String) : SQLiteOpenHelper(context, name, null, 1) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        Log.d("MyLogs", "--- create DataBase ---")
        sqLiteDatabase.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "fio text,"
                + "age integer,"
                + "post text,"
                + "cost integer" + ");")

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }

    companion object {
        val version = 1
    }


}
