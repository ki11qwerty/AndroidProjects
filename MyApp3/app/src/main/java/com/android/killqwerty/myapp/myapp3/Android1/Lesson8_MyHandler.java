package com.android.killqwerty.myapp.myapp3.Android1;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.android.killqwerty.myapp.myapp3.Android1.Lesson7_8;

import java.lang.ref.WeakReference;

public class Lesson8_MyHandler extends Handler {
    private WeakReference<Lesson7_8> wrActivity;

    Lesson8_MyHandler(Lesson7_8 activity) {
        wrActivity = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Lesson7_8 activity = wrActivity.get();
        if (activity != null) {
            activity.pb.setVisibility(View.VISIBLE);
            activity.tvInHandler.setText("Загружено файлов : " + msg.what);
            if (msg.what == 10) {
                activity.btnHandlerRun.setEnabled(true);
                activity.pb.setVisibility(View.INVISIBLE);
            }
        }
    }
}