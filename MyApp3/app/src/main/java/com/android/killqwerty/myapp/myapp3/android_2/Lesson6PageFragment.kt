package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.provider.Telephony
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.killqwerty.myapp.myapp3.R

class Lesson6PageFragment : Fragment() {
    var pageNumber: Int? = 0
    val myBTAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = arguments?.getInt(ARGUMENT_PAGE_NUMBER, 1)
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
        //TODO: захуярить сюда проверку разрешения, пока включил в ручную эт зашкварно
        val listView = view.findViewById<ListView>(R.id.a2l6_listview)
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
                    listOfItem.add(Item(address,text))
                } while (c.moveToNext())
                c.close()
            }
            listView.adapter = SmsAdapter(listOfItem,this.activity!!.applicationContext) // todo тут чет с контексотом херня нужно нот нул

        }
    }

    fun configureSecondFragment(view: View) { // тут будет настройка сенсоров
        val sensManager = view.context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensors = sensManager.getSensorList(Sensor.TYPE_ALL)
        var myString = ""
        for(x in 0 until sensors.size){
            myString += "------------------------------------------------\n"
            myString += "имя - ${sensors[x].name}\n"
            myString += "тип - ${sensors[x].type}\n"
            myString += "шаг - ${sensors[x].resolution}\n"
            myString += "максимальное значение - ${sensors[x].maximumRange}\n"
            myString += "производство  - ${sensors[x].vendor}\n"
            myString += "------------------------------------------------\n\n\n"
        }
        view.findViewById<TextView>(R.id.a2l6_sensors_tv).text = myString

    }

    fun configureThirdFragment(view: View) { // а тут уже будет настройка блютус          TODO - убрать эти грязные танцы, господи кошмар какой
        view.findViewById<Button>(R.id.a2l6_bluetooth_btn_find).setOnClickListener { findBT(view) }
        val enableBT = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        if (myBTAdapter == null){
            Toast.makeText(context,"устройство не поддерживает BlueTooth",Toast.LENGTH_LONG).show()
        }
        if (myBTAdapter?.isEnabled == false){

            startActivityForResult(enableBT,REQUEST_ENABLE_BT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK)
            Toast.makeText(context,"blueTooth активирован",Toast.LENGTH_LONG).show()
        if (resultCode == RESULT_CANCELED)
            Toast.makeText(context,"отменено",Toast.LENGTH_LONG).show()
    }

    fun findBT(view: View){
        if(myBTAdapter?.isEnabled == false) {
            Toast.makeText(context,"bluetooth не активен",Toast.LENGTH_SHORT).show()
            return
        }
        view.findViewById<TextView>(R.id.a2l6_bluetooth_tv).text = "идет поиск устройств"
        myBTAdapter!!.startDiscovery()
        val myIFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        receiver = object : BroadcastReceiver(){
            override fun onReceive(c: Context?, intent: Intent?) {
                val action = intent?.action
                when(action){
                    BluetoothDevice.ACTION_FOUND -> {
                        val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                        val deviceName = device.name
                        val deviceHardwareAddress = device.address
                        myString += "$deviceName \n $deviceHardwareAddress \n\n"
                        setMyTv()

                    }
                }
            }
        }
        context!!.applicationContext.registerReceiver(receiver,myIFilter)
        var cancel = view.findViewById<Button>(R.id.a2l6_bluetooth_btn_cancel)
        cancel.visibility = View.VISIBLE
        cancel.setOnClickListener { stopAndInvis(it) }

    }
    fun setMyTv(){
        val myTextView: TextView = view!!.findViewById(R.id.a2l6_bluetooth_tv)
        myTextView.text = myString
    }
    fun stopAndInvis(cancel: View){
        cancel.visibility = View.INVISIBLE
        if(myBTAdapter != null)
            myBTAdapter.cancelDiscovery()

    }

    companion object {
        var myString = ""
        lateinit var receiver: BroadcastReceiver
        const val REQUEST_ENABLE_BT = 10001
        const val ARGUMENT_PAGE_NUMBER = "arg_page_num"
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
        myView.findViewById<TextView>(R.id.a2l6_item_body).text = items[position].body
        return myView
    }
}