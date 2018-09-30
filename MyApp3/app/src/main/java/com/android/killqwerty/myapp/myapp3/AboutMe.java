package com.android.killqwerty.myapp.myapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AboutMe extends AppCompatActivity {
    Button buttPrev;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me);
        setMyButtons();
    }

    public void setMyButtons() {
        buttPrev = findViewById(R.id.button_backward_1);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutMe.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
