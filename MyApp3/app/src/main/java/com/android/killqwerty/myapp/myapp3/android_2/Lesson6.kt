package com.android.killqwerty.myapp.myapp3.android_2

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.support.v4.app.*
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.killqwerty.myapp.myapp3.R

class Lesson6 : FragmentActivity() {
    lateinit var viewPager: ViewPager
    lateinit var pagerAdapter: PagerAdapter
    var listOfItem = mutableListOf<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson6)
        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED)
            requestIsOK = true
        else
            requestSMS()
        createViews()
    }

    fun createViews() {
        setContentView(R.layout.android2_lesson6)
        viewPager = findViewById(R.id.and2_les6_viewpager)
        pagerAdapter = MyFragmentPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        viewPager.offscreenPageLimit = 2



        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageScrollStateChanged(position: Int) {

            }

            override fun onPageSelected(position: Int) { // можно и сюда выгрузить всю логику из создани PageFragment, но пока и так работает, для учебы и так нормально я думаю вышло
                when (position) {  // TODO: уже лучше... надо придумать как сделать чтобы отображение смс шло с первого запуска если есть разрешение...
                    0 -> {
                        if (requestIsOK && listOfItem.isEmpty() ) {
                            val listView = findViewById<ListView>(R.id.a2l6_listview)
                            val c = contentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, null, null, null, null)
                            var address: String
                            var addressIDX: Int
                            var text: String
                            var textIDX: Int
                            if (c != null) {
                                c.moveToFirst()
                                do {
                                    addressIDX = c.getColumnIndex(Telephony.Sms.Inbox.ADDRESS)
                                    textIDX = c.getColumnIndex(Telephony.Sms.BODY)
                                    address = c.getString(addressIDX)
                                    text = c.getString(textIDX)
                                    listOfItem.add(Item(address, text))
                                } while (c.moveToNext())
                                c.close()
                            }
                            listView.adapter = SmsAdapter(listOfItem, applicationContext)
                        }
                    }
                }
            }
        })
    }

    fun requestSMS() {
        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            requestIsOK = false
            Toast.makeText(applicationContext, "приложению нужно разрешение на чтение смс", Toast.LENGTH_LONG).show()
        }
        else
            requestIsOK = true
        if (Build.VERSION.SDK_INT >= 23) {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_SMS)) {
                Toast.makeText(applicationContext, "чтобы отобразить смс на этом уроке, нужно разрешение на чтение смс", Toast.LENGTH_SHORT).show()
            }
            requestPermissions(arrayOf(android.Manifest.permission.READ_SMS), 1)
        }
    }

    companion object {
        val PAGE_COUNT = 3
        var requestIsOK = false
    }
}

private class MyFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return Lesson6PageFragment.create(position)
    }

    override fun getCount(): Int {
        return Lesson6.PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "sms"
            1 -> return "sensors"
            2 -> return "bluetooth"
        }
        return null
    }
}

data class Item(var address: String, var body: String)
class SmsAdapter(val items: MutableList<Item>, var c: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myView = convertView
        if (myView == null) {
            myView = LayoutInflater.from(c).inflate(R.layout.android2_lesson6_item, null)
        }

        myView!!.findViewById<TextView>(R.id.a2l6_item_address).text = items[position].address
        myView.findViewById<TextView>(R.id.a2l6_item_body).text = items[position].body
        return myView
    }
}
