package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

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
 * TODO: https://www.youtube.com/watch?v=DsVAP2F9c1U, допилить завтра фрагменты, и занятся пунктом номер 5------------------------------------30.10! горит уже все нахуй
 *************************************************************************************************/
public class Lesson7_8 extends Activity implements View.OnClickListener{
    Button btnPrev, btnHandler, btnLoadSave, btnJson, btnWebView, btnFragments, btnDB, btnAddFr1,
    btnAddFr2, btnRemoveFr1, btnRemoveFr2, btnSwapFr1, btnSwapFr2;
    Lesson8_fragment1 fragment1;
    Lesson8_fragment2 fragment2;

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
    void setMyButtonsForFragments(){
        btnAddFr1 = findViewById(R.id.lesson8_1fragment_add);
        btnAddFr2 = findViewById(R.id.lesson8_2fragment_add);
        btnRemoveFr1 = findViewById(R.id.lesson8_1fragment_remove);
        btnRemoveFr2 = findViewById(R.id.lesson8_2fragment_remove);
        btnSwapFr1 = findViewById(R.id.lesson8_1fragment_swap);
        btnSwapFr2 = findViewById(R.id.lesson8_2fragment_swap);

        btnAddFr1.setOnClickListener(this);
        btnAddFr2.setOnClickListener(this);
        btnRemoveFr1.setOnClickListener(this);
        btnRemoveFr2.setOnClickListener(this);
        btnSwapFr1.setOnClickListener(this);
        btnSwapFr2.setOnClickListener(this);
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
                setContentView(R.layout.lesson8_layout_for_fragments);
                setMyButtonsForFragments();
                break;
            case R.id.lesson8_btn_database:
                break;
                /*        lesson8_fragments           */
            case R.id.lesson8_1fragment_add:
                setFragment(fragment1 = new Lesson8_fragment1(), R.id.lesson8_frame1);
                break;
            case R.id.lesson8_2fragment_add:
                setFragment(fragment2 = new Lesson8_fragment2(), R.id.lesson8_frame2);
                break;
            case R.id.lesson8_1fragment_remove:
                removeFragment(getFragmentManager().findFragmentById(R.id.lesson8_frame1));
                break;
            case R.id.lesson8_2fragment_remove:
                removeFragment(getFragmentManager().findFragmentById(R.id.lesson8_frame2));
                break;
            case R.id.lesson8_1fragment_swap:
                swapFragment();
                break;
            case R.id.lesson8_2fragment_swap:
                swapFragment();
                break;
        }
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
        FragmentManager fragmentManager = getFragmentManager();
        if (fragment != null && fragmentManager.findFragmentById(fragment.getId()) == null) {
            fragmentManager.beginTransaction()
                    .add(id, fragment)
                    .commit();
        }
    }

    void removeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragment != null && fragmentManager.findFragmentById(fragment.getId()) != null) {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

    void swapFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (checkingTrue()) {
            if (getFragmentManager().findFragmentById(R.id.lesson8_frame1).equals(fragment1)) {
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
        FragmentManager fragmentManager = getFragmentManager();
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
