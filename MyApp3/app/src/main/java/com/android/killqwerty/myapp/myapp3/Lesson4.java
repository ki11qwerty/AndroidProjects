package com.android.killqwerty.myapp.myapp3;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

//best pracice
//посмотреть как советуют делать диалоги Андройд
//контекстное меню
//и дз

public class Lesson4 extends AppCompatActivity {
    Button buttPrev, buttLesson4Alert, buttLesson4Single, buttLesson4Multi, buttLesson4Manual,
            buttLesson4Data, buttLesson4Time;
    AlertDialog alertDialog, alertSingle, alertMulti, alertManual;
    AlertDialog.Builder alertDB, singleDB, multiDB, manualDB;
    DatePickerDialog dp;
    TimePickerDialog tp;
    View cl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.lesson4);
        cl = getLayoutInflater().inflate(R.layout.lesson4, null, false);
        setContentView(cl);
        createAlertDialogs();
        setMyButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.submenuBlack: {
                cl.setBackgroundColor(getResources().getColor(R.color.colorBlack));
                break;
            }
            case R.id.submenuBlue: {
                cl.setBackgroundColor(getResources().getColor(R.color.colorBlue));
            }
            case R.id.submenuRed: {
                cl.setBackgroundColor(getResources().getColor(R.color.colorRed));
            }
            case R.id.submenuWhite: {
                cl.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            }
            case R.id.submenuGreen: {
                cl.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }
            case R.id.submenuGray: {
                cl.setBackgroundColor(getResources().getColor(R.color.colorGray));
            }
            case R.id.submenuYellow: {
                cl.setBackgroundColor(getResources().getColor(R.color.colorYellow));
            }
            case R.id.submenuDefault: {
                cl.setBackgroundColor(getResources().getColor(R.color.colorBlue));
            }
        }
            return super.onOptionsItemSelected(item);
    }

    public void setMyButtons() {
        buttPrev = findViewById(R.id.button_lesson4_prev);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lesson4.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttLesson4Alert = findViewById(R.id.button_lesson4_alert);
        buttLesson4Alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });
        buttLesson4Single = findViewById(R.id.button_lesson4_Single);
        buttLesson4Single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertSingle.show();
            }
        });
        buttLesson4Multi = findViewById(R.id.button_lesson4_Multi);
        buttLesson4Multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertMulti.show();
            }
        });
        buttLesson4Manual = findViewById(R.id.button_lesson4_Manual);
        buttLesson4Manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertManual.show();
            }
        });
        buttLesson4Data = findViewById(R.id.button_lesson4_data);
        buttLesson4Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.show();
            }
        });
        buttLesson4Time = findViewById(R.id.button_lesson4_time);
        buttLesson4Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tp.show();
            }
        });
    }

    public void createAlertDialogs() {
        final CharSequence[] items = {"Jameson", "Chivas 12y.o.", "Chivas 18y.o.", "Glenmorangie",
                "Olmeca silver", "Olmeca gold", "Beefeater", "Hennessy VS", "Hennessey VSOP",
                "Hennessey X.O.", "Havana Club", "Mojito", "Pina Colada", "B52"};
        final CharSequence[] itemsForMulti = {"безналичная оплата", "вызов курьера",
                "связатся с оператором после заказа"};
        boolean defaultAnswers[] = {false, false, true};
        alertDB = new AlertDialog.Builder(this);
        alertDB.setTitle("AlertDialog")
                .setMessage("Нажмите \"OK\" чтобы выйти ")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(getApplicationContext());
                        if (toast != null)
                            toast.cancel();
                        toast.makeText(getApplicationContext(),
                                "Okay, lesson finished", Toast.LENGTH_SHORT).show();
                        Lesson4.this.finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(getApplicationContext());
                        if (toast != null)
                            toast.cancel();
                        toast.makeText(getApplicationContext(),
                                "Cancel", Toast.LENGTH_LONG).show();
                        dialogInterface.cancel();
                    }
                })
                .setNeutralButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(getApplicationContext());
                        if (toast != null)
                            toast.cancel();
                        toast.makeText(getApplicationContext(),
                                "ой да все вы так говорите...", Toast.LENGTH_LONG).show();
                    }
                })
                .setIcon(R.drawable.iconki11);
        alertDialog = alertDB.create();
        singleDB = new AlertDialog.Builder(this);
        singleDB.setTitle("Выберите напиток")
                .setIcon(R.drawable.iconki11)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        Toast toast = new Toast(getApplicationContext());
                        toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Lesson4.this.finish();
                    }
                });
        alertSingle = singleDB.create();
        multiDB = new AlertDialog.Builder(this);
        multiDB.setTitle("Выберите услугу")
                .setIcon(R.drawable.iconki11)
                .setMultiChoiceItems(itemsForMulti, defaultAnswers,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                Log.d("TAG", itemsForMulti[i] + "" + b);

                            }
                        })
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Lesson4.this.finish();
                    }
                });
        alertMulti = multiDB.create();
        //Далее происходит какая то дичь с расположением кнопок в алерте. как то надо исправить потом -------------------------------------------
        manualDB = new AlertDialog.Builder(this);
        manualDB.setPositiveButton("exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Lesson4.this.finish();
            }
        })
                .setTitle("созданный из кода Layout");
        LinearLayout lt = new LinearLayout(this);
        lt.addView(new CheckBox(this));
        for (int i = 0; i < 3; i++) {
            final Button b = new Button(this);
            b.setText(Integer.toString(i));
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(b.getContext(), b.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            lt.addView(b);
            lt.addView(new CheckBox(this));
        }
        manualDB.setView(lt);
        alertManual = manualDB.create();
        dp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //какого хрена отчет месяца начинается с 0 мне пока не понятно)
               Log.d("TAG",""+year+"."+(month+1)+"."+day);
               Toast.makeText(getApplicationContext(),
                       "вы выбрали дату - "+day+","+(month + 1)+","+year,Toast.LENGTH_LONG).show();
            }
        },2018,01,17);
        tp = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Log.d("TAG",i+":"+i1);
                Toast.makeText(getApplicationContext(),
                        "вы выбрали время - "+i+":"+i1,Toast.LENGTH_LONG).show();
            }
        },10,20,true);
    }
}