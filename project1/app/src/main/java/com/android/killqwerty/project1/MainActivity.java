package com.android.killqwerty.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
//    TextView ivText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String[] array = this.getResources().getStringArray(R.array.my_str_array);
//        StringBuilder sBuilder = new StringBuilder();
//        for (int i = 0; i < array.length; i++)
//            sBuilder.append(array[i] + "\n");
//        ivText.setText(sBuilder.toString());
        button = findViewById(R.id.button);
    }
}