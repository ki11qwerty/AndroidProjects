package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R

class Lesson6PageFragment : Fragment() {
    var pageNumber: Int? = 0

    //    static PageFragment newInstance(int page) {
//        PageFragment pageFragment = new PageFragment();
//        Bundle arguments = new Bundle();
//        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
//        pageFragment.setArguments(arguments);
//        return pageFragment;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = arguments?.getInt(ARGUMENT_PAGE_NUMBER, 1)
        //   setMyButtons()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        when (pageNumber) {
            0 -> {
                val myView = inflater.inflate(R.layout.android2_lesson6_fragment_sms, container, false)
                configureFirstFragment(myView)
                return myView
            }
            1 -> {
                val myView = inflater.inflate(R.layout.android2_lesson6_fragment_sensors, container, false)
                configureSecondFragment(myView)
                return myView
            }
            2 -> {
                val myView = inflater.inflate(R.layout.android2_lesson6_fragment_bluetooth, container, false)
                configureThirdFragment(myView)
                return myView
            }
        }
        return null
    }

    fun configureFirstFragment(view: View) { // тут будет настройка смс фрагмента
        view.findViewById<Button>(R.id.button1).setOnClickListener { Toast.makeText(context, "чпонь кнопка 1", Toast.LENGTH_SHORT).show() }
    }

    fun configureSecondFragment(view: View) { // тут будет настройка сенсоров
        view.findViewById<Button>(R.id.button2).setOnClickListener { Toast.makeText(context, "чпонь кнопка 2", Toast.LENGTH_SHORT).show() }
    }

    fun configureThirdFragment(view: View) { // а тут уже будет настройка блютус
        view.findViewById<Button>(R.id.button3).setOnClickListener { Toast.makeText(context, "чпонь кнопка 3", Toast.LENGTH_SHORT).show() }
    }

    companion object {
        val ARGUMENT_PAGE_NUMBER = "arg_page_num"
        fun create(page: Int): Lesson6PageFragment {
            val pageFragment = Lesson6PageFragment()
            val arguments = Bundle()
            arguments.putInt(ARGUMENT_PAGE_NUMBER,page)
            pageFragment.arguments = arguments
            return pageFragment

        }
    }
}