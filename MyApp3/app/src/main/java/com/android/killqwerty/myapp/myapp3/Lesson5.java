package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Lesson5 extends AppCompatActivity {
    Button buttPrev;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.lesson5);
        super.onCreate(savedInstanceState);
        setMyButtons();
    }
    public void setMyButtons(){
        buttPrev = findViewById(R.id.button_lesson5_prev);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
