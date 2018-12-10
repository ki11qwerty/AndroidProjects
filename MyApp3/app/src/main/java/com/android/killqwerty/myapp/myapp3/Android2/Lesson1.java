package com.android.killqwerty.myapp.myapp3.Android2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.killqwerty.myapp.myapp3.R;

import java.util.ArrayList;
import java.util.Random;

public class Lesson1 extends AppCompatActivity {
    public static final String DB_NAME = "MyDataBaseLesson1";
    public static final String MY_TAG = "MyLogs";
    //TODO: ДЗ подъехало - перекатить все из свитч в методы
    //TODO: Захерачить загрузку бд и заполнения колекции в асинктаск, а то чет жирно
    //TODO: запилить заполнения массив , внутри метода с проверкой, и будет счастье
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
    Button btnOpenFields, btnLoad, btnDelete, btnExample, btnAddNew, btnUpdate, btnCancel,
            btnDelete1Element, BtnDelete1ElementInLayout;
    EditText etFio, etAge, etPost, etCost, etIdSet, etDelete1Element;
    Lesson1DbHelper dbHelper;
    LinearLayout allPersonsLayout, fieldsLayout, delete1ElementLayout;
    ArrayList<Person> persons;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android2_lesson1);
        setButtonsAndView();
        dbHelper = new Lesson1DbHelper(this, DB_NAME);
        allPersonsLayout = findViewById(R.id.andr2_lesson1_linearl_in_scroll);
        delete1ElementLayout = findViewById(R.id.andr2_lesson1_linear_delete_element);
        fieldsLayout = findViewById(R.id.andr2_lesson1_fields_linear);
        persons = new ArrayList<>();

        fieldsLayout.setVisibility(View.GONE);
        etIdSet.setVisibility(View.GONE);
        delete1ElementLayout.setVisibility(View.GONE);
    }
    public void setButtonsAndView(){
        setOnClick();
        btnOpenFields = findViewById(R.id.btn_a2_l1_open_fields);
        btnDelete = findViewById(R.id.btn_a2_l1_delete);
        btnLoad = findViewById(R.id.btn_a2_l1_load);
        btnExample = findViewById(R.id.btn_a2l1_example);
        btnAddNew = findViewById(R.id.andr2_lesson1_add_new);
        btnUpdate = findViewById(R.id.andr2_lesson1_btn_update);
        btnCancel = findViewById(R.id.andr2_lesson1_cancel);
        btnDelete1Element = findViewById(R.id.andr2_lesson1_btn_delete1element);
        BtnDelete1ElementInLayout = findViewById(R.id.andr2_lesson1_btn_delete1element_in_layout);

        btnOpenFields.setOnClickListener(listener);
        btnDelete.setOnClickListener(listener);
        btnLoad.setOnClickListener(listener);
        btnExample.setOnClickListener(listener);
        btnAddNew.setOnClickListener(listener);
        btnUpdate.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);
        btnDelete1Element.setOnClickListener(listener); //
        BtnDelete1ElementInLayout.setOnClickListener(listener); //

        etFio = findViewById(R.id.a2l1_et_f_i_o);
        etAge = findViewById(R.id.a2l1_et_age);
        etPost = findViewById(R.id.a2l1_et_post);
        etCost = findViewById(R.id.a2l1_et_cost);
        etIdSet = findViewById(R.id.a2l1_et_set_id);
        etDelete1Element = findViewById(R.id.andr2_lesson1_et_delete1element); //

    }

    public void setOnClick() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                int id;
                String fio;
                int age;
                String post;
                int cost;
                switch (view.getId()) {
                    case R.id.btn_a2_l1_open_fields:
                        btnAddNew.setText("add+");
                        etIdSet.setVisibility(View.GONE);
                        if(fieldsLayout.getVisibility() == View.GONE){
                            fieldsLayout.setVisibility(View.VISIBLE);
                            break;
                        }
                        break;
                    case R.id.andr2_lesson1_add_new:
                        if (etAge.getText().toString().equalsIgnoreCase("") ||
                                etCost.getText().toString().equalsIgnoreCase("") ||
                                etPost.getText().toString().equalsIgnoreCase("") ||
                                etFio.getText().toString().equalsIgnoreCase("")) {
                            Toast.makeText(getApplicationContext(), "не верно заполнены поля",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if(etIdSet.getVisibility() == View.GONE) {                                  // если выбран add
                            fio = etFio.getText().toString();
                            age = Integer.parseInt(etAge.getText().toString());
                            post = etPost.getText().toString();
                            cost = Integer.parseInt(etCost.getText().toString());
                            cv.put("fio", fio);
                            cv.put("age", age);
                            cv.put("post", post);
                            cv.put("cost", cost);
                            long rowID = db.insert("mytable", null, cv);
                            Log.d(MY_TAG, "row inserted, ID = " + rowID);
                            fieldsLayout.setVisibility(View.GONE);
                            break;
                        }
                        else if(etIdSet.getVisibility() == View.VISIBLE && etIdSet.getText().toString()
                                .equalsIgnoreCase("")){                                  // не заполнено поле id в режиме update
                            Toast.makeText(getApplicationContext(),"введите ID", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        else if (etIdSet.getVisibility() == View.VISIBLE){ // если выбран update
                            String idToUpdate = etIdSet.getText().toString();
                            fio = etFio.getText().toString();
                            age = Integer.parseInt(etAge.getText().toString());
                            post = etPost.getText().toString();
                            cost = Integer.parseInt(etCost.getText().toString());
                            cv.put("fio", fio);
                            cv.put("age", age);
                            cv.put("post", post);
                            cv.put("cost", cost);
                            int idCount = db.update("mytable",cv,"id = ?",new String[] {idToUpdate});
                            Toast.makeText(getApplicationContext(),idCount+" запись изменена id = "+idToUpdate,Toast.LENGTH_SHORT).show();
                            fieldsLayout.setVisibility(View.GONE);
                            etIdSet.setVisibility(View.GONE);
                            break;
                        }

                    case R.id.btn_a2_l1_load:
                        persons.clear();
                        Cursor c = db.query("mytable", null, null, null, null, null, null);
                        if (c.moveToFirst()) {
                            int idColIndex = c.getColumnIndex("id");
                            int fioColIndex = c.getColumnIndex("fio");
                            int ageColIndex = c.getColumnIndex("age");
                            int postColIndex = c.getColumnIndex("post");
                            int costColIndex = c.getColumnIndex("cost");

                            do {
                                //впринципе меня пока и устроит вот так вот) дальше в поток запилить, если зажористо будет
                                id = c.getInt(idColIndex);
                                fio = c.getString(fioColIndex);
                                age = Integer.parseInt(c.getString(ageColIndex));
                                post = c.getString(postColIndex);
                                cost = Integer.parseInt(c.getString(costColIndex));

                               Person person = new Person(id,fio,age,post,cost);
                               persons.add(person);
                                Log.d(MY_TAG,
                                        "ID = " + id +
                                                ", name = " + fio +
                                                ", age = " + age +
                                                ", post = " + post +
                                                ", cost = " + cost);

                            } while (c.moveToNext());
                            fillScrollView(persons);
                        } else
                            allPersonsLayout.removeAllViews();
                            Log.d(MY_TAG, "0 rows");
                        c.close();
                        break;
                    case R.id.btn_a2_l1_delete:
                        int clearCount = db.delete("mytable", null, null);
                        persons.clear();
                        Log.d(MY_TAG, "deleted rows count = " + clearCount);
                        break;
                    case R.id.btn_a2l1_example:
                        createRandomFields();
                        break;
                    case R.id.andr2_lesson1_btn_update:
                        btnAddNew.setText("update");
                        fieldsLayout.setVisibility(View.VISIBLE);
                        etIdSet.setVisibility(View.VISIBLE);
                        break;
                    case R.id.andr2_lesson1_cancel:
                        etIdSet.setVisibility(View.GONE);
                        fieldsLayout.setVisibility(View.GONE);
                        break;
                    case R.id.andr2_lesson1_btn_delete1element:
                        fieldsLayout.setVisibility(View.GONE);
                        etIdSet.setVisibility(View.GONE);
                        delete1ElementLayout.setVisibility(View.VISIBLE);
                        break;
                    case R.id.andr2_lesson1_btn_delete1element_in_layout:
                        if (etDelete1Element.getText().toString().equalsIgnoreCase(""))
                            break;
                        String idInEt = etDelete1Element.getText().toString();
                        int delCount = db.delete("mytable", "id = "+idInEt,null);
                        Toast.makeText(getApplicationContext(),delCount+" запись удалена id = "+idInEt,Toast.LENGTH_SHORT).show();
                        delete1ElementLayout.setVisibility(View.GONE);

                }
                dbHelper.close();
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
    public void fillScrollView(ArrayList<Person> persons){
        allPersonsLayout.removeAllViews();

        for(Person p: persons){
            View viewItem = getLayoutInflater().inflate(R.layout.android2_lesson1_item,null);
            TextView id = viewItem.findViewById(R.id.a2l1_item_id);
            TextView age = viewItem.findViewById(R.id.a2l1_item_age);
            TextView fio = viewItem.findViewById(R.id.a2l1_item_fio);
            TextView cost = viewItem.findViewById(R.id.a2l1_item_cost);
            TextView post = viewItem.findViewById(R.id.a2l1_item_post);
            id.setText(""+p.getId());
            fio.setText(p.getFio());
            age.setText(""+p.getAge());
            cost.setText(""+p.getCost()+"руб");
            post.setText(p.getPost());
            //TODO: на следующем уроке зафигачить сюда контекстное меню, для получения id и удаления из бд, с последующим обновлением коллекции
            allPersonsLayout.addView(viewItem);
        }

    }
    private class Person{
        int id;
        String fio;
        int age;
        String post;
        int cost;
        private Person(int id, String fio, int age, String post, int cost){
            this.id = id;
            this.age = age;
            this.fio = fio;
            this.post = post;
            this.cost = cost;
        }

        public int getAge() {
            return age;
        }

        public int getCost() {
            return cost;
        }

        public int getId() {
            return id;
        }

        public String getFio() {
            return fio;
        }

        public String getPost() {
            return post;
        }
    }

}