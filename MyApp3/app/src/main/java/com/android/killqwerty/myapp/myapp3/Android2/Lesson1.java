package com.android.killqwerty.myapp.myapp3.Android2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.killqwerty.myapp.myapp3.R;

public class Lesson1 extends AppCompatActivity {
    View.OnClickListener listener;
    Button btnAdd, btnLoad, btnDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android2_lesson1);
        setButtons();
    }
    public void setButtons(){
        setOnClick();
        btnAdd = findViewById(R.id.btn_a2_l1_add);
        btnDelete = findViewById(R.id.btn_a2_l1_delete);
        btnLoad = findViewById(R.id.btn_a2_l1_load);
        btnAdd.setOnClickListener(listener);
        btnDelete.setOnClickListener(listener);
        btnLoad.setOnClickListener(listener);

    }

    public void setOnClick() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_a2_l1_add:
                        addToDb();
                        break;
                    case R.id.btn_a2_l1_load:
                        loadDb();
                        break;
                    case R.id.btn_a2_l1_delete:
                        deleteDb();
                        break;
                }
            }
        };

    }

    public void addToDb() {
        Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();
    }

    public void loadDb() {
        Toast.makeText(getApplicationContext(),"laod",Toast.LENGTH_SHORT).show();
    }

    public void deleteDb() {
        Toast.makeText(getApplicationContext(),"delete",Toast.LENGTH_SHORT).show();
    }

}
