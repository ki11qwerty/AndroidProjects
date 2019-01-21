package com.android.killqwerty.myapp.myapp3.Android2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.android.killqwerty.myapp.myapp3.R;

public class Lesson2 extends Activity {
    Button exit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android2_lesson2);
        setMyButtonsAndViews();

    }
    public void setMyButtonsAndViews(){
        exit = findViewById(R.id.andr2_lesson2_btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
