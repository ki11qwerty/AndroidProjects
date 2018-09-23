package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Lesson5 extends AppCompatActivity {
    static String task_date;
    static String task_name;
    static final short LENGTH_IDS_ARRAY = 23;
    static String typeOfView;
    private ArrayList<Person> persons;
    private ArrayList<Lesson5Task> tasks;
    private LinearLayout allPersons;
    public int[] idForDrawable;
    ListView listView;
    GridView gridView;
    ListView listViewTasks;
    View ListLayout, ListLayoutWithListView, ListLayoutWithGridView, MyTasks;
    Button buttPrev, buttList, buttListAdapter, buttGrid, buttTask, buttNewTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.lesson5);
        super.onCreate(savedInstanceState);
        ListLayout = getLayoutInflater().inflate(R.layout.lesson5_persons_view,
                null, false);
        ListLayoutWithListView = getLayoutInflater().inflate(R.layout.lesson5_person_listview,
                null, false);
        ListLayoutWithGridView = getLayoutInflater().inflate(R.layout.lesson5_person_grid_view,
                null,false);
        MyTasks = getLayoutInflater().inflate(R.layout.lesson5_task_list_view,
                null,false);
        tasks = new ArrayList<>();
        setMyButtons();
    }

    public void setMyButtons() {
        buttPrev = findViewById(R.id.button_lesson5_prev);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttList = findViewById(R.id.button_lesson5_listview);
        buttList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(ListLayout);
                allPersons = findViewById(R.id.all_persons);
                createPersonList(100);
                fillList();

            }
        });
        buttListAdapter = findViewById(R.id.button_lesson5_listview_with_adapter);
        buttListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeOfView = "List";
                setContentView(ListLayoutWithListView);
                listView = findViewById(R.id.lv_persons_listview);
                createPersonList(5000);
                fillListWithAdaptor();
            }
        });
        buttGrid = findViewById(R.id.button_lesson5_gridview);
        buttGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeOfView = "Grid";
                setContentView(ListLayoutWithGridView);
                gridView = findViewById(R.id.grid_view1);
                createPersonList(5000);
                fillGridViewWithAdaptor();

            }
        });
        buttTask = findViewById(R.id.button_lesson5_tasks);
        buttTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(MyTasks);
                buttNewTask = findViewById(R.id.lesson5_new_task_button);
                buttNewTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createNewTask();
                        printInfo();
                    }
                });
            }
        });
    }

    private void createPersonList(int size) {
        Random random = new Random();
        persons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Person p = new Person();
            p.setName("Qwerty" + i);
            p.setEmail("Qwerty" + i + "@gmail.com");
            p.setTelephone("8-555-" + (1000000 + random.nextInt(8999999)));

            p.setAvatar(createRandomAvatar());

            persons.add(p);
        }
    }

    private void fillList() {
        allPersons.removeAllViews();
        for (Person p : persons) {
            View personElementView = createPersonElementView(p);
            allPersons.addView(personElementView);

        }
    }
    public void fillGridViewWithAdaptor(){
        PersonAdapter personAdapter = new PersonAdapter(persons, this);
        gridView.setAdapter(personAdapter);
    }
    public void fillListWithAdaptor() {
        PersonAdapter personAdapter = new PersonAdapter(persons, this);
        listView.setAdapter(personAdapter);
    }

    private View createPersonElementView(final Person p) {
        View v = getLayoutInflater().inflate(R.layout.listitem_persons, null);
        TextView tvName = v.findViewById(R.id.tvName);
        tvName.setText(p.getName());
        String email = p.getEmail();
        if (email == null)
            email = "";
        String telephone = p.getTelephone();
        if (telephone == null)
            telephone = "";
        String separator = (p.getEmail() != null &&
                p.getTelephone() != null) ? "," : "";
        TextView tvContacts = v.findViewById(R.id.tvContacts);
        tvContacts.setText(email + separator + telephone);
        ImageView imAvatar = v.findViewById(R.id.imAvatar);
        imAvatar.setImageDrawable(p.getAvatar());
        createRandomAvatar();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), p.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private Drawable createRandomAvatar() {
        if(idForDrawable == null)
            fillIdForDrawable();
        Random random = new Random();
        int num = random.nextInt(LENGTH_IDS_ARRAY);
        return getResources().getDrawable(idForDrawable[num]);

//--------------------так выходило затратно по времени >1000ms для каждого вызова-------------------

//        Random random = new Random();
//        int num = 1 + random.nextInt(23);
//        String drawableName = "friend"+num;
//        int res = getResources().getIdentifier(drawableName,"drawable",getPackageName());
//        return getResources().getDrawable(res);

// ------------------а так бесила длинна кода для заполнения но хорошая скорость 1000ms для первого
// ------------------запуска и 350ms для последующих-----------------------------------------------
//        switch (num) {
//            case 1:
//                return getResources().getDrawable(R.drawable.friend1);
//            case 2:
//                return getResources().getDrawable(R.drawable.friend2);
//            case 3:
//                return getResources().getDrawable(R.drawable.friend3);
//            case 4:
//                return getResources().getDrawable(R.drawable.friend4);
//            case 5:
//                return getResources().getDrawable(R.drawable.friend5);
//            case 6:
//                return getResources().getDrawable(R.drawable.friend6);
//            case 7:
//                return getResources().getDrawable(R.drawable.friend7);
//            case 8:
//                return getResources().getDrawable(R.drawable.friend8);
//            case 9:
//                return getResources().getDrawable(R.drawable.friend9);
//            case 10:
//                return getResources().getDrawable(R.drawable.friend10);
//            case 11:
//                return getResources().getDrawable(R.drawable.friend11);
//            case 12:
//                return getResources().getDrawable(R.drawable.friend12);
//            case 13:
//                return getResources().getDrawable(R.drawable.friend13);
//            case 14:
//                return getResources().getDrawable(R.drawable.friend14);
//            case 15:
//                return getResources().getDrawable(R.drawable.friend15);
//            case 16:
//                return getResources().getDrawable(R.drawable.friend16);
//            case 17:
//                return getResources().getDrawable(R.drawable.friend17);
//            case 18:
//                return getResources().getDrawable(R.drawable.friend18);
//            case 19:
//                return getResources().getDrawable(R.drawable.friend19);
//            case 20:
//                return getResources().getDrawable(R.drawable.friend20);
//            case 21:
//                return getResources().getDrawable(R.drawable.friend21);
//            case 22:
//                return getResources().getDrawable(R.drawable.friend22);
//            case 23:
//                return getResources().getDrawable(R.drawable.friend23);
//
//        }
//        return getResources().getDrawable(R.drawable.friend1);
    }
    public void fillIdForDrawable(){
        idForDrawable = new int[LENGTH_IDS_ARRAY];
        String nameRes = "friend";
        for (int i = 0;i<idForDrawable.length; i++){
            idForDrawable[i] = getResources().getIdentifier(nameRes + i,
                    "drawable",getPackageName());
        }
    }

    class Person {
        private String name;
        private String telephone;
        private String email;
        private Drawable avatar;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setAvatar(Drawable dr) {
            this.avatar = dr;
        }

        public Drawable getAvatar() {
            return avatar;
        }
    }

    public class PersonAdapter extends BaseAdapter {
        ArrayList<Person> persons;
        Context c;
        private PersonAdapter(ArrayList<Person> persons, Context c) {
            this.persons = persons;
            this.c = c;
        }
        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Person getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                if(typeOfView.equals("List")) {
                    convertView = LayoutInflater.from(c).inflate(R.layout.listitem_persons, null);
                }
                else
                    convertView = LayoutInflater.from(c).
                            inflate(R.layout.lesson5_person_view_grid_item,null);
                fillView(convertView,position);
            return convertView;
        }
        private void fillView(View v, int position){
            final Person p = getItem(position);
            TextView tvName = v.findViewById(R.id.tvName);
            tvName.setText(p.getName());
            String email = p.getEmail();
            if (email == null)
                email = "";
            String telephone = p.getTelephone();
            if (telephone == null)
                telephone = "";
            String separator = (p.getEmail() != null &&
                    p.getTelephone() != null) ? "," : "";
            TextView tvContacts = v.findViewById(R.id.tvContacts);
            tvContacts.setText(email + separator + telephone);
            ImageView imAvatar = v.findViewById(R.id.imAvatar);
            imAvatar.setImageDrawable(p.getAvatar());
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), p.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void showDateDialog(){
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(),"datepicker");
    }

    public static  class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            task_date = day+"."+(month+1)+"."+year;
        }
    }
//\
    public void createNewTask(){          // чет нифига у меня не получилось... выводит все разом сука такая
        final EditText et = new EditText(this);
        AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("введите название")
                .setView(et)
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        task_name = et.getText().toString();
                    }
                }).create();
        alert.show();
        showDateDialog();
        final Lesson5Task lt = new Lesson5Task(task_name,task_date);
        tasks.add(lt);
        //printInfo();
    }
    public void printInfo(){
        Toast.makeText(this,tasks.get(0).getName()+","+tasks.get(0).getDate(),Toast.LENGTH_LONG).show();
    }
    public class Lesson5Task{
        private String name;
        private String date;
        boolean isDone = false;
        Lesson5Task(String name, String date){
            this.date = date;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public void done(){
            isDone = true;
        }
    }
}
