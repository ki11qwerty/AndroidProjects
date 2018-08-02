package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity4th extends AppCompatActivity {
    Button buttPre, buttNext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void setMyButtons() {
        buttPrev = findViewById(R.id.button_backward_1);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity1st.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttNext = findViewById(R.id.button_forward_1);
        buttNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity1st.class);
                startActivity(intent);
            }
        });
    }
}
