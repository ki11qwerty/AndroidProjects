package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.Telephony
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.telephony.SmsManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.killqwerty.myapp.myapp3.R
import java.util.jar.Manifest
import java.util.zip.Inflater

class Lesson6PageFragment : Fragment() {
    var pageNumber: Int? = 0

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
        val listOfItem = mutableListOf<Item>()
        view.findViewById<Button>(R.id.button1).setOnClickListener { Toast.makeText(context, "чпонь кнопка 1", Toast.LENGTH_SHORT).show() }
        //TODO: захуярить сюда проверку разрешения, пока включил в ручную эт пиздец
        val listView = view.findViewById<ListView>(R.id.a2l6_listview)
        //val cr = object : ContentResolver(context){}
        if(ContextCompat.checkSelfPermission(context!!.applicationContext, android.Manifest.permission.READ_SMS)
            != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context,"приложению нужно разрешение на чтение смс",Toast.LENGTH_LONG).show()
        }
        else {
            val c = context?.contentResolver?.query(Telephony.Sms.Inbox.CONTENT_URI, null, null, null, null)
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
                   // Log.d("MYTAG", "$address :::: $text\n\n")
                    listOfItem.add(Item(address,text))
                } while (c.moveToNext())
                c.close()
            }
            listView.adapter = SmsAdapter(listOfItem,this.activity!!.applicationContext) // todo тут чет с контексотом херня нужно нот нул

        }
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
            arguments.putInt(ARGUMENT_PAGE_NUMBER, page)
            pageFragment.arguments = arguments
            return pageFragment

        }
    }
}
data class Item(var address :String,var body : String)
class SmsAdapter(val items : MutableList<Item>, var c : Context) : BaseAdapter(){

    override fun getCount(): Int {
       return items.size
    }

    override fun getItem(position: Int): Any {
       return items[position]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getView(position: Int,convertView: View?, parent: ViewGroup?): View {
        var myView = convertView
        if(myView == null) {
            myView = LayoutInflater.from(c).inflate(R.layout.android2_lesson6_item, null)
        }

        myView!!.findViewById<TextView>(R.id.a2l6_item_address).text = items[position].address
        myView!!.findViewById<TextView>(R.id.a2l6_item_body).text = items[position].body
        return myView
    }
}