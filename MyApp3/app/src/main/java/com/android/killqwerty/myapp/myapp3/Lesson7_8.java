package com.android.killqwerty.myapp.myapp3;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**************************************************************************************************
 * План:
 * TODO: 1 кнопка переход в layout с БД, два поля - имя, фамилия. 3 кнопки - записать, читать, стереть
 * -есть 2 кнопка переход в layout с фрагментами, три кнопки для переключения фрагментов
 * TODO: 3 кнопка переход в layout или новое активити с какой нибудь приколюхой JSON
 * -есть 4 кнопка webView
 * TODO: 5 кнопка сохранение в файл
 * TODO: 6 кнопка AsyncTask и Handler, Layout с TextView две кнопки для handler или AsyncTask, метод
 *     для приостановки потока на короткое время и итерацию в TextView
 *
 *                                                                    дата начала : 23.10
 *                                                                     закончить бы до : 1.11
 *                                    список прилетевших нежданчиков:
 * Fragments оказалось куда больше материала чем на уроке дали =(
 * Support Library
 * FragmentManager и Fragment переехали в сапорт, пришлось додумывать самому) все прошло успешно
 *
 *
 * TODO: https://www.youtube.com/watch?v=DsVAP2F9c1Uе
 *************************************************************************************************/
public class Lesson7_8 extends FragmentActivity implements View.OnClickListener {
    static final String FILE_NAME = "MyFile.txt";
    static final String MY_TAG = "myLogs";
    static final short EXTERNAL_STORAGE = 1;
    static final short SD_MEMORY = 2;
    Button btnPrev, btnHandler, btnLoadSave, btnJson, btnWebView, btnFragments, btnDB, btnAddFr1,
            btnAddFr2, btnRemoveFr1, btnRemoveFr2, btnSwapFr1, btnSwapFr2, btnLoad, btnSave,
            btnDelete, btnSdLoad, btnSdSave, btnSdDelete;
    Lesson8_fragment1 fragment1;
    Lesson8_fragment2 fragment2;
   View.OnClickListener onClickLoadSave;
   View.OnClickListener onClickFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson7_8);
        setMyButtons();
    }

    private void setMyButtons() {
        btnPrev = findViewById(R.id.lesson7_btn_prev_page);
        btnHandler = findViewById(R.id.lesson8_btn_handler_asynctask);
        btnLoadSave = findViewById(R.id.lesson8_btn_save_load);
        btnJson = findViewById(R.id.lesson8_btn_json);
        btnWebView = findViewById(R.id.lesson8_btn_webview);
        btnFragments = findViewById(R.id.lesson8_btn_fragments);
        btnDB = findViewById(R.id.lesson8_btn_database);

        btnPrev.setOnClickListener(this);
        btnHandler.setOnClickListener(this);
        btnLoadSave.setOnClickListener(this);
        btnJson.setOnClickListener(this);
        btnWebView.setOnClickListener(this);
        btnFragments.setOnClickListener(this);
        btnDB.setOnClickListener(this);

    }

    void setMyButtonsForFragments() {
        setOnClickForFragments();
        btnAddFr1 = findViewById(R.id.lesson8_1fragment_add);
        btnAddFr2 = findViewById(R.id.lesson8_2fragment_add);
        btnRemoveFr1 = findViewById(R.id.lesson8_1fragment_remove);
        btnRemoveFr2 = findViewById(R.id.lesson8_2fragment_remove);
        btnSwapFr1 = findViewById(R.id.lesson8_1fragment_swap);
        btnSwapFr2 = findViewById(R.id.lesson8_2fragment_swap);

        btnAddFr1.setOnClickListener(onClickFragments);
        btnAddFr2.setOnClickListener(onClickFragments);
        btnRemoveFr1.setOnClickListener(onClickFragments);
        btnRemoveFr2.setOnClickListener(onClickFragments);
        btnSwapFr1.setOnClickListener(onClickFragments);
        btnSwapFr2.setOnClickListener(onClickFragments);
    }
    void setMyButtonsForLoadAndSave(){
        setOnClickForLoadAndSave();
        btnSave = findViewById(R.id.lesson8_btnSave);
        btnLoad = findViewById(R.id.lesson8_btnLoad);
        btnDelete = findViewById(R.id.lesson8_btnDelete);
        btnSdLoad = findViewById(R.id.lesson8_sd_btn_load);
        btnSdSave = findViewById(R.id.lesson8_sd_btn_save);
        btnSdDelete = findViewById(R.id.lesson8_sd_btn_delete);

        btnSave.setOnClickListener(onClickLoadSave);
        btnLoad.setOnClickListener(onClickLoadSave);
        btnDelete.setOnClickListener(onClickLoadSave);
        btnSdLoad.setOnClickListener(onClickLoadSave);
        btnSdSave.setOnClickListener(onClickLoadSave);
        btnSdDelete.setOnClickListener(onClickLoadSave);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lesson7_btn_prev_page:
                finish();
                break;
            case R.id.lesson8_btn_handler_asynctask:
                break;
            case R.id.lesson8_btn_save_load:
                setContentView(R.layout.lesson8_load_and_save);
                setMyButtonsForLoadAndSave();
                break;
            case R.id.lesson8_btn_json:
                break;
            case R.id.lesson8_btn_webview:
                myWebView();
                break;
            case R.id.lesson8_btn_fragments:
                setContentView(R.layout.lesson8_layout_for_fragments);
                setMyButtonsForFragments();
                break;
            case R.id.lesson8_btn_database:
                break;
        }
    }
    void setOnClickForFragments(){
        onClickFragments = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.lesson8_1fragment_add:
                        setFragment(fragment1 = new Lesson8_fragment1(), R.id.lesson8_frame1);
                        break;
                    case R.id.lesson8_2fragment_add:
                        setFragment(fragment2 = new Lesson8_fragment2(), R.id.lesson8_frame2);
                        break;
                    case R.id.lesson8_1fragment_remove:
                        removeFragment(getSupportFragmentManager().findFragmentById(R.id.lesson8_frame1));
                        break;
                    case R.id.lesson8_2fragment_remove:
                        removeFragment(getSupportFragmentManager().findFragmentById(R.id.lesson8_frame2));
                        break;
                    case R.id.lesson8_1fragment_swap:
                        swapFragment();
                        break;
                    case R.id.lesson8_2fragment_swap:
                        swapFragment();
                        break;
                }
            }
        };
    }

    void setOnClickForLoadAndSave() {
        TextView memorySdAvaliable = findViewById(R.id.lesson8_load_save_memory);
        memorySdAvaliable.setText(getFreeMemory(EXTERNAL_STORAGE)+""+getFreeMemory(SD_MEMORY));

        onClickLoadSave = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.lesson8_edit_name)).getText().toString();
                String secondName = ((EditText) findViewById(R.id.lesson8_edit_second_name)).getText().toString();
                switch (view.getId()) {
                    case R.id.lesson8_btnSave:
                        if (name.equals("") && secondName.equals(""))
                            break;
                        try {
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                    openFileOutput(FILE_NAME, MODE_APPEND)));
                            bw.write("" + name + ", " + secondName + ".");
                            bw.newLine();
                            Log.d(MY_TAG, "save complete!" + name + "," + secondName);
                            bw.flush();
                            bw.close();
                        } catch (IOException e) {
                            Log.d(MY_TAG, "something wrong with save... ERROR!!!!");
                        }
                        Toast.makeText(getApplicationContext(), "" + name + "," + secondName,
                                Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.lesson8_btnLoad:
                        TextView tv = findViewById(R.id.lesson8_load_save_tv);
                        String allNames;
                        StringBuilder sb = new StringBuilder();
                        try {
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    openFileInput(FILE_NAME)));
                            Log.d(MY_TAG, "file open");
                            while ((allNames = br.readLine()) != null) {
                                sb.append(allNames);
                                Log.d(MY_TAG, "append:" + allNames);
                            }
                            allNames = sb.toString();
                            tv.setText(allNames);
                            br.close();
                        } catch (IOException e) {
                            Log.e(MY_TAG, "something wrong with read... ERROR!!!!");
                        }
                        Toast.makeText(getApplicationContext(), "Load ему блять",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.lesson8_btnDelete:
                        deleteFile(FILE_NAME);
                        Toast.makeText(getApplicationContext(), "записи удалены",
                                Toast.LENGTH_SHORT).show();
                        Log.d(MY_TAG, "файл удален");
                        break;
                        //TODO: допилить три кнопки, для external storage
                    case R.id.lesson8_sd_btn_load:
                        if (name.equals("") && secondName.equals(""))
                            break;
                        try {
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                    openFileOutput(FILE_NAME, MODE_APPEND)));
                            bw.write("" + name + ", " + secondName + ".");
                            bw.newLine();
                            Log.d(MY_TAG, "save complete!" + name + "," + secondName);
                            bw.flush();
                            bw.close();
                        } catch (IOException e) {
                            Log.d(MY_TAG, "something wrong with save... ERROR!!!!");
                        }
                        Toast.makeText(getApplicationContext(), "" + name + "," + secondName,
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.lesson8_sd_btn_save:
                        break;
                    case R.id.lesson8_sd_btn_delete:
                        break;
                }
            }
        };
    }
    public String getFreeMemory(short type){
        if (type == SD_MEMORY){
       //     return "free space : " +Environment.getExternalStorageDirectory().se
       //     return "free space : " +(int)((Environment.getExternalStorageDirectory().getTotalSpace() /1000)/1000);

        }
//        if (type == EXTERNAL_STORAGE){
//          return "free space : "+(int)(((Environment.getExternalStorageDirectory().
//                  getFreeSpace()) / 1024) / 1024)+"Mb";
//        }
        return "";
    }

    void myWebView() {
        setContentView(R.layout.lesson8_webview);
        final EditText editText;
        editText = findViewById(R.id.lesson8_webview_edittext);
        Button btnSearch;
        final WebView webGoogle;
        webGoogle = findViewById(R.id.lesson8_webview_google);
        webGoogle.getSettings().setJavaScriptEnabled(true);
        btnSearch = findViewById(R.id.lesson8_webview_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText() != null) {
                    webGoogle.loadUrl("https://www.google.ru/search?q=" + editText.getText());
                }
            }
        });

    }

    void setFragment(Fragment fragment, int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragment != null && fragmentManager.findFragmentById(fragment.getId()) == null) {
            fragmentManager.beginTransaction()
                    .add(id, fragment)
                    .commit();
        }
    }

    void removeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragment != null && fragmentManager.findFragmentById(fragment.getId()) != null) {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

    void swapFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (checkingTrue()) {
            if (getSupportFragmentManager().findFragmentById(R.id.lesson8_frame1).equals(fragment1)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.lesson8_frame1, fragment2 = new Lesson8_fragment2())
                        .replace(R.id.lesson8_frame2, fragment1 = new Lesson8_fragment1())
                        .commit();
            } else {
                fragmentManager.beginTransaction()
                        .replace(R.id.lesson8_frame1, fragment1 = new Lesson8_fragment1())
                        .replace(R.id.lesson8_frame2, fragment2 = new Lesson8_fragment2())
                        .commit();
            }
        }
    }

    boolean checkingTrue() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.lesson8_frame1) != null &&
                fragmentManager.findFragmentById(R.id.lesson8_frame2) != null)
            return true;
        return false;
    }

    // serialise - пустой интерфейс, флаг, для разрешения сохранения класса в файл
    // parsable - интерфейс, с методами для сохранения и загрузки класса из файла, быстрее
    // handler - класс, для передачи сообщений между потоками
    // AsyncTask - класс для фоновой работы отдельно от gui потока и больших работ в фоне
    // Нет дз, нихуя не понятно, все сделать вместе с уроком №8 в одной кнопке

}
