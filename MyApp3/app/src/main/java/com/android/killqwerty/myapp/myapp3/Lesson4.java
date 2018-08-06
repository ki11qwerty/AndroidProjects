package com.android.killqwerty.myapp.myapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.Batch;

public class Lesson4 extends AppCompatActivity {
    Button buttPrev;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson4);
        setMyButtons();
    }
    public void setMyButtons(){
        buttPrev = findViewById(R.id.button_lesson_prev);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lesson4.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
