package com.android.killqwerty.myapp.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.killqwerty.myapp.myapp3.Android1.Lesson4;
import com.android.killqwerty.myapp.myapp3.Android1.Lesson5;
import com.android.killqwerty.myapp.myapp3.Android1.Lesson6;
import com.android.killqwerty.myapp.myapp3.Android1.Lesson7_8;

public class MainActivity extends AppCompatActivity {
    Button buttNext, buttLesson4, buttLesson5, buttLesson6, buttLesson7, buttExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMyButtons();
    }
    public void setMyButtons(){
        buttNext = findViewById(R.id.button_forward_main);
        buttNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutMe.class);
                startActivity(intent);
            }
        });
        buttLesson4 = findViewById(R.id.button_lesson_4_main);
        buttLesson4.setOnClickListener(new View.OnClickListener() {
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
    } }
