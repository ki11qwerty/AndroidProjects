package com.android.killqwerty.myapp.myapp3.Android2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Lesson1DbHelper extends SQLiteOpenHelper {
    public static final int version = 1;
    public Lesson1DbHelper(@Nullable Context context, String name) {
        super(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
