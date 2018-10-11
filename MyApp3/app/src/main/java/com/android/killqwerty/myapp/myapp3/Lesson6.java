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

    Button btnPrev, btnIntentNewActivity, btnCamera, btnCall, btnContacts, btnBrowser, btnGithub,
    btnMaps, btnEmail;
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
                    Toast.makeText(getApplicationContext(),"отправленное поле оказалось пустым",
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
        btnGithub = findViewById(R.id.btn_lesson6_github);
        btnMaps = findViewById(R.id.btn_lesson6_geo);
        btnEmail = findViewById(R.id.btn_lesson6_email);

        btnContacts.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnBrowser.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnMaps.setOnClickListener(this);
        btnGithub.setOnClickListener(this);
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
            case R.id.btn_lesson6_email:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:tasuke34@gmail.com"));
                //intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "tasuke34@gmail.com");
                intent.putExtra(Intent.EXTRA_STREAM,"tasuke34@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Lesson6");
                intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.text_in_email));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(Intent.createChooser(intent,"отправление письма..."));
                }
                break;
            case R.id.btn_lesson6_geo:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:48.759023,44.505723?z=16"));
                startActivity(intent);
                break;
            case R.id.btn_lesson6_github:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.github)));
                startActivity(intent);
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
