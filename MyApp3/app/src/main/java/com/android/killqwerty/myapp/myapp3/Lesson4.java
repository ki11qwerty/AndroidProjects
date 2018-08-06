package com.android.killqwerty.myapp.myapp3;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;
public class Lesson4 extends AppCompatActivity {
    Button buttPrev, buttLesson4Alert;
    AlertDialog alertDialog;
    AlertDialog.Builder alertDB;
   // Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson4);
        createAlertDialog();
        setMyButtons();
    }

    public void setMyButtons() {
        buttPrev = findViewById(R.id.button_lesson_prev);
        buttPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lesson4.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttLesson4Alert = findViewById(R.id.button_lesson_alert);
        buttLesson4Alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });
    }

    public void createAlertDialog() {
        alertDB =  new AlertDialog.Builder(this);
        alertDB.setTitle("AlertDialog")
                .setMessage("Мой первый алерт)")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Lesson4.this.finish();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNeutralButton("ваще похуй!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(getApplicationContext());
                        if (toast != null)
                            toast.cancel();
                        toast.makeText(getApplicationContext(),
                                "ваще похуй значит", Toast.LENGTH_LONG).show();
                    }
                });
      alertDialog = alertDB.create();
    }
}
