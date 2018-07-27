package com.android.killqwerty.myapp.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button button11;
    Button button13;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_table);
        setButtons();
    }
    public void setButtons(){
        button11 = findViewById(R.id.button11);
        button13 = findViewById(R.id.button13);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(toast !=null){
                    toast.cancel();
                }
                toast = Toast.makeText(getApplicationContext(), button11.getText().toString() +
                "is pressed!!",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(getApplicationContext(),
                        "да! йебой! пошел нахуй!",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
