package com.android.killqwerty.myapp.androidbeer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    Toast tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButton();
    }
    public void setButton(){
        bt1 = findViewById(R.id.button2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tt != null) {
                    tt.cancel();
                }
                tt = Toast.makeText(getApplicationContext(),"@string/button",Toast.LENGTH_LONG);
                tt.show();
            }
        });
    }
}
