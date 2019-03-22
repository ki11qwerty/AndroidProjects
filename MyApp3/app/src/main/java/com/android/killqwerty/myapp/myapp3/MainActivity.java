package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.killqwerty.myapp.myapp3.android1.Lesson4;
import com.android.killqwerty.myapp.myapp3.android1.Lesson5;
import com.android.killqwerty.myapp.myapp3.android1.Lesson6;
import com.android.killqwerty.myapp.myapp3.android1.Lesson7_8;
import com.android.killqwerty.myapp.myapp3.android_2.Lesson1;
import com.android.killqwerty.myapp.myapp3.android_2.Lesson2;
import com.android.killqwerty.myapp.myapp3.android_2.Lesson3Main;
import com.android.killqwerty.myapp.myapp3.android_2.Lesson3serv;

public class MainActivity extends Activity {
    Button btnNext, btnLesson4, buttLesson5, buttLesson6, buttLesson7, buttExit,btnAndroid1,
            btnAndroid2, btnAndr2Lesson1, btnAndr2Lesson2, btnAndr2Lesson3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_android1);
        setMyButtonsAndroid1();
    }
    public void setMyButtonsAndroid1(){
        btnNext = findViewById(R.id.button_forward_main);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutMe.class);
                startActivity(intent);
            }
        });
        btnLesson4 = findViewById(R.id.button_lesson_4_main);
        btnLesson4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lesson4.class);
                startActivity(intent);
            }
        });
        buttLesson5 = findViewById(R.id.button_lesson_5_main);
        buttLesson5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this , Lesson5.class);
               startActivity(intent);
            }
        });
        buttLesson6 = findViewById(R.id.button_lesson6_main);
        buttLesson6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lesson6.class);
                startActivity(intent);
            }
        });
        buttLesson7 = findViewById(R.id.button_lesson7_main);
        buttLesson7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lesson7_8.class);
                startActivity(intent);
            }
        });
        buttExit = findViewById(R.id.buttonExit);
        buttExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAndroid2 = findViewById(R.id.main_android2_btn);
        btnAndroid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main_android2);
                setMyButtonsAndroid2();
            }
        });

    }
    public void setMyButtonsAndroid2(){
        btnNext = findViewById(R.id.button_forward_main);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutMe.class);
                startActivity(intent);
            }
        });
        buttExit = findViewById(R.id.buttonExit);
        buttExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAndroid1 = findViewById(R.id.main2_android1_btn);
        btnAndroid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main_android1);
                setMyButtonsAndroid1();
            }
        });
        btnAndr2Lesson1 = findViewById(R.id.btn_andr2_lesson1);
        btnAndr2Lesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lesson1.class);
                startActivity(intent);
            }
        });
        btnAndr2Lesson2 = findViewById(R.id.btn_andr2_lesson2);
        btnAndr2Lesson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lesson2.class);
                startActivity(intent);
            }
        });
        btnAndr2Lesson3 = findViewById(R.id.btn_andr2_lesson3);
        btnAndr2Lesson3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lesson3Main.class);
                startActivity(intent);
            }
        });
    }
}
