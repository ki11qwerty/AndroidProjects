package com.android.killqwerty.myapp.myapp3.Android1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import com.android.killqwerty.myapp.myapp3.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Lesson5 extends AppCompatActivity {
    static final int LENGTH_IDS_ARRAY = 23;
    static final int SWITCH_TO_DONE = 1;
    static final int SWITCH_TO_UNDONE = 2;
    static final int DELETE_ITEM = 3;


    static String task_date;
    static String task_name;
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
                null, false);
        MyTasks = getLayoutInflater().inflate(R.layout.lesson5_task_list_view,
                null, false);
        tasks = new ArrayList<>(); // это перенести в метод заполнения листа
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
                listViewTasks = findViewById(R.id.lesson5_list_task);
                registerForContextMenu(listViewTasks);
                createExampleTasks(10);
                fillTasksListWithAdapter();
                buttNewTask = findViewById(R.id.lesson5_new_task_button);
                buttNewTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createNewTask();
                    }
                });
            }
        });
    }

    public void createExampleTasks(int count){     //для теста
        for(int i = 0; i< count; i++){
            Lesson5Task l = new Lesson5Task();
            l.setName("Новая заметка "+(i+1));
            l.setDate("1.1.1990");
            tasks.add(l);
        }
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

    public void fillGridViewWithAdaptor() {
        PersonAdapter personAdapter = new PersonAdapter(persons, this);
        gridView.setAdapter(personAdapter);
    }

    public void fillListWithAdaptor() {
        PersonAdapter personAdapter = new PersonAdapter(persons, this);
        listView.setAdapter(personAdapter);
    }

    public void fillTasksListWithAdapter() {
        TasksAdapter tasksAdapter = new TasksAdapter(tasks, this);
        listViewTasks.setAdapter(tasksAdapter);

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
        if (idForDrawable == null)
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

    public void fillIdForDrawable() {
        idForDrawable = new int[LENGTH_IDS_ARRAY];
        String nameRes = "friend";
        for (int i = 0; i < idForDrawable.length; i++) {
            idForDrawable[i] = getResources().getIdentifier(nameRes + i,
                    "drawable", getPackageName());
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
            if (convertView == null)
                if (typeOfView.equals("List")) {
                    convertView = LayoutInflater.from(c).inflate(R.layout.listitem_persons, null);
                } else
                    convertView = LayoutInflater.from(c).
                            inflate(R.layout.lesson5_person_view_grid_item, null);
            fillView(convertView, position);
            return convertView;
        }

        private void fillView(View v, int position) {
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

    public void showDateDialog() {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
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
            task_date = day + "." + (month + 1) + "." + year;
            ((Lesson5) getActivity()).addNewTasks();
            // ((YourActivityClassName)getActivity()).yourPublicMethod(); на поиски этой панацеи
            // было убито 12 часов с пробой других костылей

        }
    }

    public void createNewTask() {
        final EditText et = new EditText(this);
        AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("введите название")
                .setView(et)
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        task_name = et.getText().toString();
                        showDateDialog();
                    }
                }).create();
        alert.show();
    }

    public void addNewTasks() {
        if (task_name != null && task_date != null) {
            String name = task_name;
            String date = task_date;
            final Lesson5Task lt = new Lesson5Task();
            lt.setName(name);
            lt.setDate(date);
            tasks.add(lt);
            task_name = null;
            task_date = null;
            ((BaseAdapter)listViewTasks.getAdapter()).notifyDataSetChanged();
        }
    }
    class Lesson5Task{
        private String name;
        private String date;
        boolean done = false;

        public void setName(String name) {
            this.name = name;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public void done(){
            done = true;
        }
        public void unDone(){                  //для теста, потом удалить-------------------------------
            done = false;
        }
        public boolean isDone(){
            return done;
        }
    }
    public class TasksAdapter extends BaseAdapter {
        ArrayList<Lesson5Task> tasks;
        Context c;

        TasksAdapter(ArrayList<Lesson5Task> tasks, Context c) {
            this.tasks = tasks;
            this.c = c;
        }

        @Override
        public Lesson5Task getItem(int position) {
            return tasks.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getCount() {
            return tasks.size();
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null)
                view = LayoutInflater.from(c).inflate(R.layout.lesson5_task_item, null);
            fillView(view, position);
            return view;
        }

        private void fillView(View view, int position) {
            final Lesson5Task l = getItem(position);
            TextView myName = view.findViewById(R.id.lesson5_task_name);
            myName.setText(l.getName());
            TextView myDate = view.findViewById(R.id.lesson5_task_date);
            myDate.setText(l.getDate());
            if (l.isDone() == true) {
                myName.setBackgroundResource(R.color.colorGreen);
                view.setBackgroundResource(R.color.colorGreen);
                myDate.setBackgroundResource(R.color.colorGreen);
            } else {
                myName.setBackgroundResource(R.color.colorWhite);
                view.setBackgroundResource(R.color.colorWhite);
                myDate.setBackgroundResource(R.color.colorWhite);
            }
            view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                }
            });                                        //-----------доработать контесктное меню для каждого вью, можно сделать инфлейтер через XML и добавить логику для каждого пункта-----------------------
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),""+l.getName(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, SWITCH_TO_DONE, 0, "Пометить как выполненное");
        menu.add(0, DELETE_ITEM, 0, "Удалить");
        menu.add(0, SWITCH_TO_UNDONE, 0, "Пометить как невыполненное");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case SWITCH_TO_DONE:
                tasks.get(info.position).done();
                Toast.makeText(this,"Помечено как выполненное",
                        Toast.LENGTH_SHORT).show();
                ((BaseAdapter) listViewTasks.getAdapter()).notifyDataSetChanged();
                return true;
            case DELETE_ITEM:
                tasks.remove(info.position);
                Toast.makeText(this,"Заметка удалена",
                        Toast.LENGTH_SHORT).show();
                ((BaseAdapter) listViewTasks.getAdapter()).notifyDataSetChanged();
                return true;
            case SWITCH_TO_UNDONE:
                tasks.get(info.position).unDone();
                Toast.makeText(this,"Помечено как невыполненное",
                        Toast.LENGTH_SHORT).show();
                ((BaseAdapter) listViewTasks.getAdapter()).notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
