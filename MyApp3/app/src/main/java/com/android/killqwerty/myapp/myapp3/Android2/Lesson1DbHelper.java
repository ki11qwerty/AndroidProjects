package com.android.killqwerty.myapp.myapp3.Android2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.android.killqwerty.myapp.myapp3.Android2.Lesson1.MY_TAG;

public class Lesson1DbHelper extends SQLiteOpenHelper {
    public static final int version = 1;
    public Lesson1DbHelper(@Nullable Context context, String name) {
        super(context,name,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(MY_TAG,"--- create DataBase ---");
        sqLiteDatabase.execSQL("create table mytable ("
        + "id integer primary key autoincrement,"
        + "fio text,"
        + "age integer,"
        + "post text,"
        + "cost integer" + ");");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
