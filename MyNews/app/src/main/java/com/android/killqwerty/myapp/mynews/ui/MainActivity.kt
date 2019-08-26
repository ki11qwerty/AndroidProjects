package com.android.killqwerty.myapp.mynews.ui


/*
todo: toolbar
todo: webView
todo: сохранение состояния во втором фрагменте
todo: убрать обрезание дискрипшена (немного тупанул =) )
todo: постепенная подгрузка списка
todo: навести порядок в верстке
 */
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.android.killqwerty.myapp.mynews.R

class MainActivity : AppCompatActivity(), IShowArticle {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        supportFragmentManager.beginTransaction().add(R.id.frame, MainFragment())
            .commit()
    }

    override fun showingArticle() {
        supportFragmentManager.beginTransaction().replace(R.id.frame, FullScreenNewsFragment())
            .addToBackStack(null)
            .commit()

    }

    override fun openUrl(url: String) {
        Log.d("MYTAG",url)
        val bundle = Bundle()
        bundle.putString("url",url)
        val fragment = WebViewFragment.getNewInstance(bundle)
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment)
            .commit()
    }
}