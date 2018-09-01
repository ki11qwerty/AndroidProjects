package com.android.killqwerty.myapp.myapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttNext, buttLesson4, buttLesson5, buttExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMyButtons();
    }
    public void setMyButtons(){
        buttNext = findViewById(R.id.button_forward_main);
        buttNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity1st.class);
                startActivity(intent);
            }
        });
        buttLesson4 = findViewById(R.id.button_lesson_4_main);
        buttLesson4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lesson4.class);
                startActivity(intent);
            }
        });
        buttLesson5 = findViewById(R.id.button_lesson_5_main);
        buttLesson5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(getApplicationContext());
                if (toast !=null)
                    toast.cancel();
                toast.makeText(getApplicationContext(),"да ты ебать 4ый еще не сделал =)",
                        Toast.LENGTH_LONG).show();
            }
        });
        buttExit = findViewById(R.id.buttonExit);
        buttExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
