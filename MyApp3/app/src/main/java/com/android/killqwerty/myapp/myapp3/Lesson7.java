package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class Lesson7 extends Activity implements View.OnClickListener {
    Button btnPrev;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson7);
        setMyButtons();
    }

    private void setMyButtons() {
        btnPrev = findViewById(R.id.lesson7_btn_prev_page);
        btnPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lesson7_btn_prev_page:
                finish();
                break;
        }
    }
}
