package com.android.killqwerty.myapp.myapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button button;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtons();
    }
    public void setButtons(){
        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(getApplicationContext(), R.string.pivko,
                        Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
