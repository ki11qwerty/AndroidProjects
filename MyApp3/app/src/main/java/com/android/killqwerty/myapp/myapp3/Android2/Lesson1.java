package com.android.killqwerty.myapp.myapp3.Android2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.killqwerty.myapp.myapp3.R;

public class Lesson1 extends AppCompatActivity {
    public static final String DB_NAME = "MyDataBaseLesson1";
    public static final String myLog = ""
    View.OnClickListener listener;
    Button btnAdd, btnLoad, btnDelete;
    EditText etFio, etAge, etPost, etCost;
    Lesson1DbHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android2_lesson1);
        setButtonsAndView();
        dbHelper = new Lesson1DbHelper(this, DB_NAME);
    }
    public void setButtonsAndView(){
        setOnClick();
        btnAdd = findViewById(R.id.btn_a2_l1_add);
        btnDelete = findViewById(R.id.btn_a2_l1_delete);
        btnLoad = findViewById(R.id.btn_a2_l1_load);
        btnAdd.setOnClickListener(listener);
        btnDelete.setOnClickListener(listener);
        btnLoad.setOnClickListener(listener);
        etFio = findViewById(R.id.a2l1_et_f_i_o);
        etAge = findViewById(R.id.a2l1_et_age);
        etPost = findViewById(R.id.a2l1_et_post);
        etCost = findViewById(R.id.a2l1_et_cost);

    }

    public void setOnClick() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                switch (view.getId()) {
                    case R.id.btn_a2_l1_add:
                        String fio = etFio.getText().toString();
                        String age = etAge.getText().toString();
                        String post = etPost.getText().toString();
                        String cost = etCost.getText().toString();
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
      //  SQLiteDatabase db = dbHelper.getWritableDatabase();

        Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();
    }

    public void loadDb() {
        Toast.makeText(getApplicationContext(),"laod",Toast.LENGTH_SHORT).show();
    }

    public void deleteDb() {
        Toast.makeText(getApplicationContext(),"delete",Toast.LENGTH_SHORT).show();
    }

}