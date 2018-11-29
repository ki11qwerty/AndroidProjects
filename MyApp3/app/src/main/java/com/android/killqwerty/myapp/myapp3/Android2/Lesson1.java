package com.android.killqwerty.myapp.myapp3.Android2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.killqwerty.myapp.myapp3.R;

import java.util.Random;

public class Lesson1 extends AppCompatActivity {
    public static final String DB_NAME = "MyDataBaseLesson1";
    public static final String MY_TAG = "MyLogs";
    String[] names = {"Иванов","Петров","Сидоров","Антонов","Песков","Никульшин","Ивлеев",
            "Захаров","Марченко","Путин","Медведев","Навальный","Дробинин","Винярский","Ильченко",
            "Крюков"};
    String[] i_o = {"А.А","А.В","Е.Г","О.Ю","Г.О","П.П","П.В","Н.О","Е.А","О.Г","В.Е","А.Н","А.Б","Б.Ю"};
    String[] posts = {"Напальник отдела","уборщик","бармен","тестировщик","царь во дворца",
            "помощник директора","системный администратор","официант","халдей","повар","шеф-повар",
            "администратор","бухгалтер","закупщик","грузчик","программист","доставщик",
            "поставщик","кладовщик","старший бухгалтер","SEO","фотограф","водитель","стажер",
            "директор",};
    View.OnClickListener listener;
    Button btnAdd, btnLoad, btnDelete, btnExample;
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
        btnExample = findViewById(R.id.btn_a2l1_example);

        btnAdd.setOnClickListener(listener);
        btnDelete.setOnClickListener(listener);
        btnLoad.setOnClickListener(listener);
        btnExample.setOnClickListener(listener);

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
                        int age = Integer.parseInt(etAge.getText().toString());
                        String post = etPost.getText().toString();
                        int cost = Integer.parseInt(etCost.getText().toString());
                        cv.put("fio",fio);
                        cv.put("age", age);
                        cv.put("post",post);
                        cv.put("cost", cost);
                        long rowID = db.insert("mytable", null, cv);
                        Log.d(MY_TAG, "row inserted, ID = " + rowID);
                        break;
                    case R.id.btn_a2_l1_load:
                        break;
                    case R.id.btn_a2_l1_delete:
                        int clearCount = db.delete("mytable", null, null);
                        Log.d(MY_TAG, "deleted rows count = " + clearCount);
                        break;
                    case R.id.btn_a2l1_example:
                        createRandomFields();
                }
                dbHelper.close();
                // TODO: допилить кнопку екзампл - в свичь, рандом генерацию персоны, и усе
            }
        };

    }

    public void createRandomFields() {
        Random rand = new Random();
        String age = ""+(rand.nextInt(43) + 18);
        String cost = ""+(((rand.nextInt(24)+1) * 1000) + 20000);
        String post = posts[rand.nextInt(posts.length)];
        String fio = names[rand.nextInt(names.length)] +" "+ i_o[rand.nextInt(i_o.length)];
        etAge.setText(age);
        etFio.setText(fio);
        etCost.setText(cost);
        etPost.setText(post);

    }

}