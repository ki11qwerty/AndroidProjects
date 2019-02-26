package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class Lesson3Service : Service() {
    override fun onCreate() {
        Log.d(MYTAG, "onCreate started")
        myJob = GlobalScope.launch { myFun() }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!myJob.isActive)
            myJob.start()
        Log.d(MYTAG, "onStartCommand started")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(MYTAG, "onDestroy service")
        myJob.cancel()
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    suspend fun myFun(){
        var count = 0
        while (myJob.isActive) {
            delay(100)
            count += 1
            Log.d(MYTAG, "$count")
            if (count % 10 == 0)
                Log.d(MYTAG,"----------------------------------------------------------------")
            if (count % 100 == 0)
                stopSelf()
        }
    }

    companion object {
        val MYTAG = "MYTAG"
        lateinit var myJob : Job
    }
}

//class MyAsync : AsyncTask<Void,Void,Unit>(){
//    override fun doInBackground(vararg p0: Void?) {
//        for (x in 0..100) {
//            Log.d("MYTAG", "-$x-")
//            Thread.sleep(10)
//        }
//    }
//
//    override fun onPostExecute(result: Unit?) {
//       // Lesson3_MyService().onDestroy()
//        Log.d("MYTAG","finish!")
//        super.onPostExecute(result)
//    }
//}