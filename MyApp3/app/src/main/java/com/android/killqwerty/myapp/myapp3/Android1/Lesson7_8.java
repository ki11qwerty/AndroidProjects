package com.android.killqwerty.myapp.myapp3.Android1;

import android.os.AsyncTask;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.killqwerty.myapp.myapp3.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

/**************************************************************************************************
 * План:
 * -УДАЛЕНО (следующий урок)
 *        кнопка переход в layout с БД, два поля - имя, фамилия. 3 кнопки - записать, читать, стереть
 * -Выполнено 1 кнопка переход в layout с фрагментами, три кнопки для переключения фрагментов
 * -Выполнено 2 кнопка переход в layout или новое активити с какой нибудь приколюхой JSON
 * -Выполнено 4 кнопка webView
 * -Выполнено 5 кнопка сохранение в файл
 * -Выполнено 6 кнопка AsyncTask и Handler, Layout с TextView две кнопки для handler или AsyncTask, метод
 *        для приостановки потока на короткое время и итерацию в TextView
 * TODO: 7 оставляю пустую кнопку, создать реальный парсер, а не это вот все, но потом, как время будет
 *
 *                                                                    дата начала : 23.10
 *                                                                    закончить бы до : 1.11
 *
 *                                                                    закончил: 18.11 =(
 *                                    список прилетевших нежданчиков:
 * Fragments оказалось куда больше материала чем на уроке дали =(
 * Support Library
 * FragmentManager и Fragment переехали в сапорт, пришлось додумывать самому) все прошло успешно
 * FileWriter второй аргумент не MODE_APPEND а True, часов 6 тупежа, чуть не забросил андройд) ну
 *      охуеть теперь
 * Handler оказался куда более крутым, и плюс почему то про слабые зависимости узнал только сейчас
 *************************************************************************************************/

public class Lesson7_8 extends FragmentActivity implements View.OnClickListener {
    static final String FILE_NAME = "MyFile.txt";
    static final String MY_TAG = "myLogs";
    static final String FILE_NAME_IN_SD = "MyFileSd.txt";
    static final String PATH_NAME_IN_SD = "MyFilesKillQwerty";
    Button btnPrev, btnHandler, btnLoadSave, btnJson, btnWebView, btnFragments, btnAddFr1,
            btnAddFr2, btnRemoveFr1, btnRemoveFr2, btnSwapFr1, btnSwapFr2, btnLoad, btnSave,
            btnDelete, btnSdLoad, btnSdSave, btnSdDelete, btnHandlerRun, btnAsync, btnINJson,
            btnJsonTest, btnJsonParser;
    ProgressBar pb;
    Lesson8_fragment1 fragment1;
    Lesson8_fragment2 fragment2;
    View.OnClickListener onClickLoadSave, onClickFragments, onClickHandler, onClickJson;
    TextView tvInHandler, asyncTv, tvJsonLeftSize, tvJsonRightSize;
    EditText etJsonName, etJsonLastName, etJsonAge;
    Lesson8_MyHandler handler;
    MyAsyncTask myAsyncTask;

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
        btnJsonParser = findViewById(R.id.lesson8_btn_json_parser);

        btnPrev.setOnClickListener(this);
        btnHandler.setOnClickListener(this);
        btnLoadSave.setOnClickListener(this);
        btnJson.setOnClickListener(this);
        btnWebView.setOnClickListener(this);
        btnFragments.setOnClickListener(this);
        btnJsonParser.setOnClickListener(this);

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

    void setMyButtonsForLoadAndSave() {
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

    void setMyButtonsForHandler() {
        setOnClickHandler();
        pb = findViewById(R.id.lesson8_progressbar);
        btnHandlerRun = findViewById(R.id.lesson8_handler_run);
        btnHandlerRun.setOnClickListener(onClickHandler);
        tvInHandler = findViewById(R.id.lesson8_handler_tv1);
        asyncTv = findViewById(R.id.lesson8_handler_tv2);
        handler = new Lesson8_MyHandler(this);
        btnAsync = findViewById(R.id.lesson8_btn_asynctask_run);
        btnAsync.setOnClickListener(onClickHandler);
        myAsyncTask = new MyAsyncTask();
    }

    void setMyButtonsForJson() {
        setOnClickJson();
        btnINJson = findViewById(R.id.lesson8_json_btn_just);
        btnJsonTest = findViewById(R.id.lesson8_json_test_btn);

        btnINJson.setOnClickListener(onClickJson);
        btnJsonTest.setOnClickListener(onClickJson);

        etJsonName = findViewById(R.id.lesson8_json__edittext_name);
        etJsonLastName = findViewById(R.id.lesson8_json__edittext_lastname);
        etJsonAge = findViewById(R.id.lesson8_json_edittext_age);
        tvJsonLeftSize = findViewById(R.id.lesson8_json_personInfo_left_size);
        tvJsonRightSize = findViewById(R.id.lesson8_json_personInfo_after_Json_right_size);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lesson7_btn_prev_page:
                finish();
                break;
            case R.id.lesson8_btn_handler_asynctask:
                setContentView(R.layout.lesson8_handler_asynctask);
                setMyButtonsForHandler();
                break;
            case R.id.lesson8_btn_save_load:
                setContentView(R.layout.lesson8_load_and_save);
                setMyButtonsForLoadAndSave();
                break;
            case R.id.lesson8_btn_json:
                setContentView(R.layout.lesson8_json_layout);
                setMyButtonsForJson();
                break;
            case R.id.lesson8_btn_webview:
                myWebView();
                break;
            case R.id.lesson8_btn_fragments:
                setContentView(R.layout.lesson8_layout_for_fragments);
                setMyButtonsForFragments();
                break;
            case R.id.lesson8_btn_json_parser:
                setContentView(R.layout.lesson8_json_parser);
                break;
        }
    }

    void setOnClickForFragments() {
        onClickFragments = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
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
        onClickLoadSave = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.lesson8_edit_name)).getText().toString();
                String secondName = ((EditText) findViewById(R.id.lesson8_edit_second_name)).getText().toString();
                TextView tv = findViewById(R.id.lesson8_load_save_tv);
                String allNames;
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
                    case R.id.lesson8_sd_btn_save:
                        if (name.equals("") && secondName.equals(""))
                            return;
                        if (!Environment.getExternalStorageState().equals(
                                Environment.MEDIA_MOUNTED)) {
                            Log.d(MY_TAG, "SD-карта не доступна: " +
                                    Environment.getExternalStorageState());
                            return;
                        }
                        try {
                            File pathSd = Environment.getExternalStorageDirectory();
                            pathSd = new File(pathSd.getAbsolutePath() + "/" + PATH_NAME_IN_SD);
                            pathSd.mkdirs();
                            File file = new File(pathSd, FILE_NAME_IN_SD);
                            try {
                                FileWriter filewriter = new FileWriter(file, true);
                                filewriter.append("" + name + ", " + secondName + ".");
                                filewriter.close();
                            } catch (IOException e) {
                                Log.d(MY_TAG, "something wrong with save in sd line-249");
                            }
                        } catch (NullPointerException e) {
                            Toast.makeText(getApplicationContext(), "каталог не найден",
                                    Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "saving",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.lesson8_sd_btn_load:
                        if (!Environment.getExternalStorageState().equals(
                                Environment.MEDIA_MOUNTED)) {
                            Log.d(MY_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
                            return;
                        }

                        File sdPath = Environment.getExternalStorageDirectory();
                        sdPath = new File(sdPath.getAbsolutePath() + "/" + PATH_NAME_IN_SD);
                        File sdFile = new File(sdPath, FILE_NAME_IN_SD);
                        try {
                            StringBuilder sb1 = new StringBuilder();
                            BufferedReader br = new BufferedReader(new FileReader(sdFile));
                            while ((allNames = br.readLine()) != null) {
                                sb1.append(allNames);
                            }
                            allNames = sb1.toString();
                            tv.setText(allNames);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.lesson8_sd_btn_delete:
                        File fileDelete = Environment.getExternalStorageDirectory();
                        fileDelete = new File(fileDelete.getAbsolutePath() + "/" + PATH_NAME_IN_SD + "/" + FILE_NAME_IN_SD);
                        if (fileDelete.delete()) {
                            Toast.makeText(getApplicationContext(), "fail deleted",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "fail not deleted",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };
    }

    void setOnClickHandler() {
        onClickHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.lesson8_handler_run:
                        handlerRun();
                        break;
                    case R.id.lesson8_btn_asynctask_run:
                        new MyAsyncTask().execute();
                }
            }
        };
    }

    public void handlerRun() {
        tvInHandler.setVisibility(View.VISIBLE);
        pb.setVisibility(View.VISIBLE);
        btnHandlerRun.setEnabled(false);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 11; i++) {
                    download(1);
                    handler.sendEmptyMessage(i);
                }
            }
        });
        t.start();
    }

    void setOnClickJson() {
        onClickJson = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.lesson8_json_btn_just:
                        String name;
                        String lastName;
                        int age;
                        if(etJsonName.getText().toString().equals("") ||
                                etJsonLastName.getText().toString().equals("") ||
                                etJsonAge.getText().toString().equals(""))
                            Toast.makeText(getApplicationContext(), "поля не заполнены", Toast.LENGTH_SHORT).show();
                        else {
                            name = etJsonName.getText().toString();
                            lastName = etJsonLastName.getText().toString();
                            age = Integer.parseInt(etJsonAge.getText().toString());
                            createPersonAndSetJson(name, lastName, age);
                        }

                        break;
                    case R.id.lesson8_json_test_btn:
                        etJsonName.setText("Android");
                        etJsonLastName.setText("KitKat");
                        etJsonAge.setText("2013");
                        break;
                }
            }
        };
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

    void download(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            Log.d(MY_TAG, "новый поток отказывается спать");
        }
    }

    public void createPersonAndSetJson(String name,String lastName,int age){
        JSONObject jsonObject;
        PersonTEMP person = new PersonTEMP(name, lastName, age);
        String allInfo = "имя: "+person.getName()+"\nфамилия: "+person.getLastName()+
                "\nвозраст: "+person.getAge()+"\ninfo: "+person.getInfo();
        tvJsonLeftSize.setText("Данные через Get'еры\n\n\n"+allInfo);
        tvJsonLeftSize.setTextColor(getResources().getColor(R.color.colorBlack));
        try {
            jsonObject = new JSONObject();
            jsonObject.put("name", person.getName());
            jsonObject.put("last name",person.getLastName());
            jsonObject.put("age",person.getAge());
            jsonObject.put("info",person.getInfo());
            tvJsonRightSize.setText("Данные в JsonObject\n\n\n"+jsonObject.toString());
            tvJsonRightSize.setTextColor(getResources().getColor(R.color.colorBlack));
        }catch (JSONException e){
            Log.d(MY_TAG,"error in creating JSON");
        }


    }

    class MyAsyncTask extends AsyncTask<Void, CharSequence, Void> {
        CharSequence[] chars = {"a", "s", "y", "n", "c", "t", "a", "s", "k", "\n",
                "i", "s", "\n", "r", "e", "a", "l", "\n\n", "l", "e", "s", "s", "o", "n", "8", " ",
                "c", "o", "m", "p", "l", "e", "t", "e"};

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btnAsync.setEnabled(false);
            pb.setVisibility(View.VISIBLE);
            asyncTv.setVisibility(View.VISIBLE);
            asyncTv.setText("");
            asyncTv.setTextColor(getResources().getColor(R.color.colorBlack));
            asyncTv.setTextSize(30);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i = 0; i < chars.length; i++) {
                    TimeUnit.MILLISECONDS.sleep(300);
                    publishProgress(chars[i]);
                }
                TimeUnit.MILLISECONDS.sleep(2000);
                publishProgress("xxx");
            } catch (InterruptedException e) {
                Log.d("MyLogs", "");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(CharSequence... values) {
            super.onProgressUpdate(values);
            pb.setVisibility(View.VISIBLE);
            if (values[0] == "xxx") {
                asyncTv.setText("=)");
                asyncTv.setTextSize(140);
                asyncTv.setTextColor(getResources().getColor(R.color.colorGreen));
            } else
                asyncTv.append(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setVisibility(View.INVISIBLE);
            btnAsync.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }
}


// serialise - пустой интерфейс, флаг, для разрешения сохранения класса в файл               (java)
// parsable - интерфейс, с методами для сохранения и загрузки класса из файла, быстрее     (android)
// handler - класс, для передачи сообщений между потоками и для исполнения кода через промежуток времени
// AsyncTask - класс для фоновой работы отдельно от gui потока и больших работ в фоне
// Нет дз, нихуя не понятно, все сделать вместе с уроком №8 в одной кнопке
