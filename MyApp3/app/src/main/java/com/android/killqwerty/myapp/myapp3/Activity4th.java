package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity4th extends AppCompatActivity {
    Button buttPrev;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout4th);
        setMyButtons();
    }
    public void setMyButtons() {
        buttPrev = findViewById(R.id.button_backward_4);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity4th.this, Activity3th.class);
                startActivity(intent);
            }
        });
    }
}
