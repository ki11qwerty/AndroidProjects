package com.android.killqwerty.myapp.myapp3;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
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
public class Lesson7_8 extends Activity implements View.OnClickListener{
    Button btnPrev, btnHandler, btnLoadSave, btnJson, btnWebView, btnFragments, btnDB, btnAddFr1,
    btnAddFr2, btnRemoveFr1, btnRemoveFr2, btnSwapFr1, btnSwapFr2;
    Lesson8_fragment1 fragment1;
    Lesson8_fragment2 fragment2;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson7_8);
        setMyButtons();
        fragment1 = new Lesson8_fragment1();
        fragment2 = new Lesson8_fragment2();
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
                setFragments(fragment1);
                // TODO: переписать весь код, запилить три метода, удаление добавление перемешивание,
                // и как то продумать логику чутка
                
//                transaction = getFragmentManager().beginTransaction();
//                transaction.add(R.id.lesson8_frame1,fragment1);
//                transaction.commit();
                break;
            case R.id.lesson8_2fragment_add:
                transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.lesson8_frame2,fragment2);
                transaction.commit();
                break;
            case R.id.lesson8_1fragment_remove:
                transaction = getFragmentManager().beginTransaction();
                transaction.remove(fragment1);
                transaction.commit();
                break;
            case R.id.lesson8_2fragment_remove:
                transaction = getFragmentManager().beginTransaction();
                transaction.remove(fragment2);
                transaction.commit();
                break;
            case R.id.lesson8_1fragment_swap:
                transaction = getFragmentManager().beginTransaction();
                transaction.replace()
                transaction.commit();
                break;
            case R.id.lesson8_2fragment_swap:
                transaction = getFragmentManager().beginTransaction();
                transaction.commit();
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
    void setFragments(Fragment fragment){
            FragmentManager fragmentManager = getFragmentManager();
            if (fragment != null && fragmentManager.findFragmentById(fragment.getId()) == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.lesson8_framelayout_fragment1, fragment)
                        .commit();
            }

            // TODO: установить вью, прикрепить два фрагмента ... как это сделать пока непонятно
    }
    // serialise - пустой интерфейс, флаг, для разрешения сохранения класса в файл
    // parsable - интерфейс, с методами для сохранения и загрузки класса из файла, быстрее
    // handler - класс, для передачи сообщений между потоками
    // AsyncTask - класс для фоновой работы отдельно от gui потока и больших работ в фоне
    // Нет дз, нихуя не понятно, все сделать вместе с уроком №8 в одной кнопке

}
