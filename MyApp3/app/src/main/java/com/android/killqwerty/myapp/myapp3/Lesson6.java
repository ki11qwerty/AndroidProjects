package com.android.killqwerty.myapp.myapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lesson6 extends AppCompatActivity {
    Button buttPrev, buttIntentNewActivity;
    EditText textToIntent;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.lesson6);
        super.onCreate(savedInstanceState);
        setMyButtons();
    }
    public void setMyButtons(){
        buttPrev = findViewById(R.id.button_lesson6_prev);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        textToIntent = findViewById(R.id.lesson6_edit_text_1);
        buttIntentNewActivity = findViewById(R.id.lesson6_button_new_activity);
        buttIntentNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textToIntent.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"поле не может быть пустым",
                            Toast.LENGTH_SHORT).show();
                String thisText = textToIntent.getText().toString();
                Intent intent = new Intent(Lesson6.this,Lesson6SecondActivity.class);
                intent.putExtra("KEY1",thisText);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });
    }
}
