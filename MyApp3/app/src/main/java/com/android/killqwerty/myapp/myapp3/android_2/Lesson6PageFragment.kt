package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.killqwerty.myapp.myapp3.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        val btn_sms = view.findViewById<Button>(R.id.a2l6_btn_sms_fragment)
        btn_sms.setOnClickListener {
            if((activity as Lesson6).fillSmsList()) // спасибо тебе великий интернет, опять такой же ступор как на яве, но уже не на весь день)
                btn_sms.visibility = View.INVISIBLE

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

    fun configureThirdFragment(view: View) { // а тут уже будет настройка блютус
        view.findViewById<Button>(R.id.a2l6_bluetooth_btn_find).setOnClickListener { findBT(view) } //..это удалить и перенести в активити. a хотяяяя... -___-
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
        myString = "идет поиск устройств"
        view.findViewById<TextView>(R.id.a2l6_bluetooth_tv).text = myString
        myBTAdapter!!.startDiscovery()
        val myIFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        myString = " "
        receiver = object : BroadcastReceiver(){
            override fun onReceive(c: Context?, intent: Intent?) {
                val action = intent?.action
                when(action){
                    BluetoothDevice.ACTION_FOUND -> {
                        val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                        if (device.name != null) {
                            val deviceName = device.name
                            val deviceHardwareAddress = device.address
                            myString += "имя девайса -$deviceName \nадрес - $deviceHardwareAddress \nкласс bluetooth - ${device.bluetoothClass}" +
                                    "\nтип девайса - ${device.type} \n\n"
                            setMyTv()
                        }

                    }
                }
            }
        }
        context!!.applicationContext.registerReceiver(receiver,myIFilter)
        var cancel = view.findViewById<Button>(R.id.a2l6_bluetooth_btn_cancel)
        GlobalScope.launch {
            for (x in 0..100){
                Log.d("RECEIVER",receiver.toString())
                delay(250)
            }
        }
        cancel.visibility = View.VISIBLE
        cancel.setOnClickListener { stopAndInvis(it) }

    }
    fun setMyTv(){
        val myTextView: TextView? = view?.findViewById(R.id.a2l6_bluetooth_tv)
        if (myTextView != null)
            myTextView.text = myString
    }
    fun stopAndInvis(cancel: View){
        cancel.visibility = View.INVISIBLE
        myString = ""  // отчистка чтобы не дублировать при следующем вызове
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