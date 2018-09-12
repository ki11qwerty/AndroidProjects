package com.android.killqwerty.myapp.myapp3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Lesson5 extends AppCompatActivity {
    private ArrayList<Person> persons;
    private LinearLayout allPersons;
    ListView listView;
    View ListLayout;
    View ListLayoutWithListView;
    Button buttPrev, buttList, buttListAdapter, buttGrid, buttTusk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.lesson5);
        super.onCreate(savedInstanceState);
        ListLayout = getLayoutInflater().inflate(R.layout.lesson5_persons_view,
                null, false);
        ListLayoutWithListView = getLayoutInflater().inflate(R.layout.lesson5_person_listview,
                null, false);
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
                setContentView(ListLayoutWithListView);
                listView = findViewById(R.id.lv_persons_listview);
                createPersonList(5000);
                fillListWithAdaptor();
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
            p.setAvatar(getResources().getDrawable(R.drawable.friend1));
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
        createRandomAvatar(imAvatar);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), p.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private void createRandomAvatar(ImageView imAvatar) {
        Random random = new Random();
        int num = 1 + random.nextInt(23);
        switch (num) {            // надо как то укоротить эту столбень потом =) этож пздц-----------
            case 1:
                imAvatar.setImageResource(R.drawable.friend1);
                break;
            case 2:
                imAvatar.setImageResource(R.drawable.friend2);
                break;
            case 3:
                imAvatar.setImageResource(R.drawable.friend3);
                break;
            case 4:
                imAvatar.setImageResource(R.drawable.friend4);
                break;
            case 5:
                imAvatar.setImageResource(R.drawable.friend5);
                break;
            case 6:
                imAvatar.setImageResource(R.drawable.friend6);
                break;
            case 7:
                imAvatar.setImageResource(R.drawable.friend7);
                break;
            case 8:
                imAvatar.setImageResource(R.drawable.friend8);
                break;
            case 9:
                imAvatar.setImageResource(R.drawable.friend9);
                break;
            case 10:
                imAvatar.setImageResource(R.drawable.friend10);
                break;
            case 11:
                imAvatar.setImageResource(R.drawable.friend11);
                break;
            case 12:
                imAvatar.setImageResource(R.drawable.friend12);
                break;
            case 13:
                imAvatar.setImageResource(R.drawable.friend13);
                break;
            case 14:
                imAvatar.setImageResource(R.drawable.friend14);
                break;
            case 15:
                imAvatar.setImageResource(R.drawable.friend15);
                break;
            case 16:
                imAvatar.setImageResource(R.drawable.friend16);
                break;
            case 17:
                imAvatar.setImageResource(R.drawable.friend17);
                break;
            case 18:
                imAvatar.setImageResource(R.drawable.friend18);
                break;
            case 19:
                imAvatar.setImageResource(R.drawable.friend19);
                break;
            case 20:
                imAvatar.setImageResource(R.drawable.friend20);
                break;
            case 21:
                imAvatar.setImageResource(R.drawable.friend21);
                break;
            case 22:
                imAvatar.setImageResource(R.drawable.friend22);
                break;
            case 23:
                imAvatar.setImageResource(R.drawable.friend23);
                break;
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

        public void setAvatar(Drawable avatar) {
            this.avatar = avatar;
        }
    }

    public class PersonAdapter extends BaseAdapter {
        ArrayList<Person> persons;
        Context c;
        public PersonAdapter(ArrayList<Person> persons, Context c) {
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
                convertView = LayoutInflater.from(c).inflate(R.layout.listitem_persons,null);
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
            createRandomAvatar(imAvatar);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), p.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
