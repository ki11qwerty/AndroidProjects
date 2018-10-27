package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**************************************************************************************************
 * План:
 * 1 кнопка переход в layout с БД, два поля - имя, фамилия. 3 кнопки - записать, читать, стереть
 * 2 кнопка переход в layout с фрагментами, три кнопки для переключения фрагментов
 * 3 кнопка переход в layout или новое активити с какой нибудь приколюхой JSON
 * 4 кнопка webView
 * 5 кнопка сохранение в файл
 * 6 кнопка AsyncTask и Handler, Layout с TextView две кнопки для handler или AsyncTask, метод
 *     для приостановки потока на короткое время и итерацию в TextView
 *
 *                                                                    дата начала : 23.10
 *                                                                     закончить бы до : 1.11
 *
 *************************************************************************************************/
public class Lesson7_8 extends Activity implements View.OnClickListener {
    Button btnPrev, btnHandler, btnLoadSave, btnJson, btnWebView, btnFragments, btnDB;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lesson7_btn_prev_page:
                finish();
                break;
            case R.id.lesson8_btn_handler_asynctask:
                break;
            case R.id.lesson8_btn_save_load:
                break;
            case R.id.lesson8_btn_json:
                break;
            case R.id.lesson8_btn_webview:
                myWebView();
                break;
            case R.id.lesson8_btn_fragments:
                break;
            case R.id.lesson8_btn_database:
                break;
        }
    }
    void myWebView(){
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
                if(editText.getText() != null){
                    webGoogle.loadUrl("https://www.google.ru/search?q="+editText.getText());
                }
            }
        });

    }
    void createMyFragments(){
       // TODO: установить вью, прикрепить два фрагмента ... как это сделать пока непонятно
    }
    // serialise - пустой интерфейс, флаг, для разрешения сохранения класса в файл
    // parsable - интерфейс, с методами для сохранения и загрузки класса из файла, быстрее
    // handler - класс, для передачи сообщений между потоками
    // AsyncTask - класс для фоновой работы отдельно от gui потока и больших работ в фоне
    // Нет дз, нихуя не понятно, все сделать вместе с уроком №8 в одной кнопке

}
