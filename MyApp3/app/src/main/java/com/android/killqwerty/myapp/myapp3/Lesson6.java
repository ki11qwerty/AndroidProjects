package com.android.killqwerty.myapp.myapp3;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Lesson6 extends AppCompatActivity {
    Button buttPrev;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.lesson6);
        super.onCreate(savedInstanceState);
        setMyButtons();
    }
    public void setMyButtons(){
        buttPrev = findViewById(R.id.button_lesson6_prev);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
