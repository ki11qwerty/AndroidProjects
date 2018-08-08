package com.android.killqwerty.myapp.myapp3;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;
public class Lesson4 extends AppCompatActivity {
    Button buttPrev, buttLesson4Alert, buttLesson4Single, buttLesson4Multi, buttLesson4Manual;
    AlertDialog alertDialog, alertSingle, alertMulti;
    AlertDialog.Builder alertDB, singleDB, multiDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson4);
        createAlertDialogs();
        setMyButtons();
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
    }

    public void createAlertDialogs() {
        final CharSequence[] items = {"Jameson", "Chivas 12y.o", "Glenmorangie", "Olmeca silver",
                "Olmeca gold", "Beefeater","Mojito",
                " Pina Colada", "B52"};
        final CharSequence[] itemsForMulti = {"Ебатся со стеной","погрузка пизды","лизать яйца"};
        boolean defaultAnswers[] = {false,false,false};
        alertDB =  new AlertDialog.Builder(this);
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
                                "Okay, lesson finished", Toast.LENGTH_LONG).show();
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
                      toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                  }
              })//.setMessage("выберите напиток")
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
                      Log.d("TAG", itemsForMulti[i]+""+b);

                  }
              })
              .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      Lesson4.this.finish();
                  }
              });
      alertMulti = multiDB.create();
    }
}