package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Lesson5 extends AppCompatActivity {
    private ArrayList<Person> persons;
    private LinearLayout allPersons;
    View ll;
    Button buttPrev,buttList,buttGrid,buttTusk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.lesson5);
        super.onCreate(savedInstanceState);
        ll = getLayoutInflater().inflate(R.layout.lesson5_persons_view,null,false);
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
                setContentView(ll);
                allPersons = (LinearLayout) findViewById(R.id.all_persons);
                createPersonList();
                fillList();

            }
        });
    }
    private void createPersonList() {
        persons = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setName("Qwerty"+i);
            p.setEmail("Qwerty"+i+"@gmail.com");
            p.setTelephone("8-555-"+i+"55-55-55");
            p.setAvatarExist(i%2==0);
            persons.add(p);
        }
    }
    private void fillList(){
        allPersons.removeAllViews();
        for (Person p : persons) {
            View personElementView = createPersonElementView(p);
            allPersons.addView(personElementView);

        }
    }
    private View createPersonElementView(final Person p){
        View v = getLayoutInflater().inflate(R.layout.listitem_persons,null);
        TextView tvName = v.findViewById(R.id.tvName);
        tvName.setText(p.getName());
        String email = p.getEmail();
        if(email==null)
            email = "";
        String telephone = p.getTelephone();
        if(telephone==null)
            telephone = "";
        String separator = (p.getEmail()!=null &&
                p.getTelephone()!=null) ? "," : "";
        TextView tvContacts = (TextView)
                v.findViewById(R.id.tvContacts);
        tvContacts.setText(email+separator+telephone);
        ImageView imAvatar = v.findViewById(R.id.imAvatar);
        createRandomAvatar(imAvatar);
        //imAvatar.setImageResource();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),p.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    private void  createRandomAvatar(ImageView imAvatar){
        Random random = new Random();
        int num = 1 + random.nextInt(11);
        switch (num){
            case 1:imAvatar.setImageResource(R.drawable.friend1);
                break;
            case 2:imAvatar.setImageResource(R.drawable.friend2);
                break;
            case 3:imAvatar.setImageResource(R.drawable.friend3);
                break;
            case 4:imAvatar.setImageResource(R.drawable.friend4);
                break;
            case 5:imAvatar.setImageResource(R.drawable.friend5);
                break;
            case 6:imAvatar.setImageResource(R.drawable.friend6);
                break;
            case 7:imAvatar.setImageResource(R.drawable.friend7);
                break;
            case 8:imAvatar.setImageResource(R.drawable.friend8);
                break;
            case 9:imAvatar.setImageResource(R.drawable.friend9);
                break;
            case 10:imAvatar.setImageResource(R.drawable.friend10);
                break;
            case 11:imAvatar.setImageResource(R.drawable.friend11);
                break;
        }
    }

     class Person {
        private String name;
        private String telephone;
        private String email;
        private boolean avatarExist;

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

        public boolean isAvatarExist() {
            return avatarExist;
        }

        public void setAvatarExist(boolean avatarExist) {
            this.avatarExist = avatarExist;
        }
    }
}
