package com.android.killqwerty.myapp.myapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lesson6SecondActivity extends AppCompatActivity {
    Button buttPrev, buttNext;
    TextView txtFromIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson6_second_activity);
        setMyButtons();
        setTextInActivity();
    }
    public void setMyButtons() {
        buttPrev = findViewById(R.id.button_backward_2);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void setTextInActivity(){
        String someText = getIntent().getExtras().getString("KEY1");
        txtFromIntent = findViewById(R.id.lesson6_text_from_intent);
        txtFromIntent.setTextSize(24);
        txtFromIntent.setText(someText);

    }
}
