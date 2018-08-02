package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity2nd extends AppCompatActivity {
    Button buttPrev, buttNext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2nd);
        setMyButtons();
    }
    public void setMyButtons() {
        buttPrev = findViewById(R.id.button_backward_2);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2nd.this, Activity1st.class);
                startActivity(intent);
            }
        });

        buttNext = findViewById(R.id.button_forward_2);
        buttNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2nd.this, Activity3th.class);
                startActivity(intent);
            }
        });
    }
}
