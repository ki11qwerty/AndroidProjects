package com.android.killqwerty.myapp.myapp3.android1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.killqwerty.myapp.myapp3.R;


public class Lesson8_fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lesson8_fragment1, container, false);
    }
}

