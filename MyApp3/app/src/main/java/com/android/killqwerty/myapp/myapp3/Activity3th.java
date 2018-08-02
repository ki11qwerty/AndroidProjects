package com.android.killqwerty.myapp.myapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity3th extends AppCompatActivity {
    Button buttPrev, buttNext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3th);
        setMyButtons();
    }
    public void setMyButtons() {
        buttPrev = findViewById(R.id.button_backward_3);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity3th.this, Activity2nd.class);
                startActivity(intent);
            }
        });

        buttNext = findViewById(R.id.button_forward_3);
        buttNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity3th.this, Activity4th.class);
                startActivity(intent);
            }
        });
    }
}
