package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Lesson6 extends Activity implements View.OnClickListener {
    static final int CAMERA_RESULT = 1;
    Button btnPrev, btnIntentNewActivity, btnCamera, btnCall, btnContacts, btnBrowser;
    EditText textToIntent;
    ImageView imageView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.lesson6);
        super.onCreate(savedInstanceState);
        setMyButtons();
        imageView = findViewById(R.id.lesson6_photo_view);
    }
    public void setMyButtons(){
        btnPrev = findViewById(R.id.button_lesson6_prev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        textToIntent = findViewById(R.id.lesson6_edit_text_1);
        btnIntentNewActivity = findViewById(R.id.lesson6_button_new_activity);
        btnIntentNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textToIntent.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"поле не может быть пустым",
                            Toast.LENGTH_SHORT).show();
                String thisText = textToIntent.getText().toString();
                Intent intent = new Intent(Lesson6.this,Lesson6SecondActivity.class);
                intent.putExtra("KEY1",thisText);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });
        btnBrowser = findViewById(R.id.button_lesson6_browser);
        btnCall = findViewById(R.id.button_lesson6_call);
        btnCamera = findViewById(R.id.button_lesson6_camera);
        btnContacts = findViewById(R.id.button_lesson6_contacts);

        btnContacts.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnBrowser.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.button_lesson6_browser:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                startActivity(intent);
                break;
            case R.id.button_lesson6_call:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+75555555555"));
                startActivity(intent);
                break;
            case R.id.button_lesson6_contacts:
                intent = new Intent(Intent.ACTION_PICK,
                        android.provider.ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
                break;
            case R.id.button_lesson6_camera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_RESULT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        if(requestCode == CAMERA_RESULT) {
            imageView.setImageBitmap((Bitmap) data.getExtras().get("data"));
        }
    }
}
