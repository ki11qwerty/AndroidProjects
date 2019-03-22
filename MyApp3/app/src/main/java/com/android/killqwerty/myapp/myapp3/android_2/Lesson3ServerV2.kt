package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Service
import android.content.Intent
import android.os.IBinder

class Lesson3ServerV2 : Service(){
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    suspend fun myFun(){

    }
}