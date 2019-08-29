package com.android.killqwerty.myapp.mynews.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.killqwerty.myapp.mynews.R

class MainActivity : AppCompatActivity(), IShowArticle {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null)
            setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction().add(R.id.frame, MainFragment())
            .commit()
    }

    override fun showingArticle() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.frame, FullScreenNewsFragment())
            .addToBackStack(null)
            .commit()

    }

    override fun openUrl(url: String) {
        val bundle = Bundle()
        bundle.putString("url",url)
        val fragment = WebViewFragment.getNewInstance(bundle)
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {onBackPressed()}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        super.onBackPressed()
    }
}