package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.killqwerty.myapp.myapp3.R

class Lesson6PageFragment : Fragment() {
     var pageNumber : Int? = 0

    //    static PageFragment newInstance(int page) {
//        PageFragment pageFragment = new PageFragment();
//        Bundle arguments = new Bundle();
//        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
//        pageFragment.setArguments(arguments);
//        return pageFragment;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = arguments?.getInt(ARGUMENT_PAGE_NUMBER,0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val myView = inflater.inflate(R.layout.android2_lesson6_fragment_sms,container)
        return view
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