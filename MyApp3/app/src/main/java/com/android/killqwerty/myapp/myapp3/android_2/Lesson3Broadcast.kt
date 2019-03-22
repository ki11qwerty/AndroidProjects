package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import com.android.killqwerty.myapp.myapp3.R
import kotlinx.android.synthetic.main.android2_lesson3_broadcast.*

class Lesson3Broadcast : Activity() {
    lateinit var tv: TextView
    lateinit var progress: ProgressBar
    lateinit var br: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.android2_lesson3_broadcast)
        setMyViews()
        br = object :BroadcastReceiver(){
            override fun onReceive(context: Context,intent: Intent){
                val myInt = intent.getIntExtra(GIVE_ME_INTEGER,0)
                tv.text = myInt.toString()
                progress.progress = myInt
                if(myInt == 0 || myInt == 100) {
                    progress.visibility = View.GONE
                }else{
                    progress.visibility = View.VISIBLE
                }
            }
        }
        val intFilter = IntentFilter(BROADCAST_ACTION)
        registerReceiver(br, intFilter)

    }

    fun setMyViews() {
        andr2_les3_broad_btn_start.setOnClickListener { startService(Intent(this, Lesson3ServerV2::class.java)) }
        andr2_les3_broad_btn_stop.setOnClickListener { stopService(Intent(this, Lesson3ServerV2::class.java)) }
        progress = findViewById(R.id.andr2_les3_progressbar)
        progress.visibility = View.INVISIBLE
        tv = findViewById(R.id.andr2_les3_broad_tv)
        progress.max = 100

    }

    companion object {
        val BROADCAST_ACTION = "com.android.killqwerty.myapp.myapp3.android_2_lesson3broadcast"
        val GIVE_ME_INTEGER = "myInteger"
    }

    override fun onDestroy() {
        unregisterReceiver(br)
        super.onDestroy()
    }
}