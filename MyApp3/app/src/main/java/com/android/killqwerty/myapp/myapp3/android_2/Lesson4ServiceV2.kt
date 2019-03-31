package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Lesson3ServerV2 : Service(){
    val myJob1 =  GlobalScope.launch { myFun() }
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       if(!myJob1.isActive){
           myJob1.start()
       }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        myJob1.cancel()
        super.onDestroy()
    }
    suspend fun myFun(){
        val intent = Intent(Lesson4Broadcast.BROADCAST_ACTION)
        for(x in 0..100) {
            delay(75)
            intent.putExtra(Lesson4Broadcast.GIVE_ME_INTEGER, x)
            sendBroadcast(intent)
        }
        delay(1000)
        for (x in 100.downTo(0)){
            delay(75)
            intent.putExtra(Lesson4Broadcast.GIVE_ME_INTEGER, x)
            sendBroadcast(intent)
        }
        stopSelf()
    }
}