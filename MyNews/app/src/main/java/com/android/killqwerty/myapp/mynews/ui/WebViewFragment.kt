package com.android.killqwerty.myapp.mynews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.android.killqwerty.myapp.mynews.R
import kotlinx.android.synthetic.main.webview_fragment.view.*

class WebViewFragment : Fragment() {
    var newsUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        newsUrl = arguments!!.getString("url")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.webview_fragment,container, false)
        val myWebView = view.my_web_view
        myWebView.settings.javaScriptEnabled = true
        myWebView.loadUrl(newsUrl)
        return view
    }

    companion object{
        fun getNewInstance(bundle: Bundle) : WebViewFragment{
            val webViewFragment = WebViewFragment()
            webViewFragment.arguments = bundle
            return webViewFragment
            }
        }
    }