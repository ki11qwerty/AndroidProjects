package com.android.killqwerty.myapp.mynews.ui


/*
готово! - webViewFragment с инстансом url
готово! - вызвать метод в мейн через fragment
нерешаемо - кризозябры в русских новостях в content

todo: toolbar - почитать про бест практис поведения кнопки в тулбаре!
todo: webView
todo: сохранение состояния во втором фрагменте
todo: убрать обрезание дискрипшена (немного тупанул =) )
todo: постепенная подгрузка списка
todo: навести порядок в верстке
 */
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
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
        Log.d("MYTAG",url)
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