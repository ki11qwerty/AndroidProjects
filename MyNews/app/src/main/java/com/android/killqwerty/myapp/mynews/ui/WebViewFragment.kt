package com.android.killqwerty.myapp.mynews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.killqwerty.myapp.mynews.R
import kotlinx.android.synthetic.main.webview_fragment.view.*

class WebViewFragment : Fragment() {
    var newsUrl : String? = arguments!!.getString("url")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.webview_fragment,container, false)
        view.web_view_tv.text = newsUrl
        return view
    }
    companion object{
        fun getNewInstance(bundle: Bundle) : WebViewFragment{
            val webViewFragment = WebViewFragment()
            webViewFragment.arguments = bundle // такс чет не работает так) разберемся, до дома ток дойду
            return webViewFragment
            }
        }
    }