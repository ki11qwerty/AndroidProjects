package com.android.killqwerty.myapp.myapp3.android_2

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import java.util.function.IntToLongFunction

class Lesson3Service : Service() {
    override fun onCreate() {
        Log.d(MYTAG, "onCreate started")
       // myJob = GlobalScope.launch { myFun() }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var time = intent?.getIntExtra(PARAM_TIME,0)
        var pi = intent?.getParcelableExtra<PendingIntent>(PARAM_PINTENT)
        GlobalScope.launch { newJob(time,startId,pi) }.start()
//
//        if (!myJob.isActive)
//            myJob.start()
//        Log.d(MYTAG, "onStartCommand started")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(MYTAG, "onDestroy service")
      //  myJob.cancel()
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

    suspend fun newJob(time: Int? ,startId : Int,pi : PendingIntent?){
        Log.d("MYTAG","вошел в метод newJob time = $time pi = $pi startid = $startId ")
        pi?.send(STATUS_START)
        if (time != null) {
            delay((time * 1000).toLong())
        var myIntent = Intent().putExtra(PARAM_RESULT, time * 1000)
            pi?.send(this,STATUS_FINISH,myIntent)
            delay(3000)
            pi?.send(STATUS_CLEAR)
            stopSelf()}
    }

    companion object {
        val MYTAG = "MYTAG"
        lateinit var myJob : Job
        val TASK_1 = 1
        val TASK_2 = 2
        val TASK_3 = 3
        val STATUS_START = 100
        val STATUS_FINISH = 200
        val STATUS_CLEAR = 300
        val PARAM_TIME = "time"
        val PARAM_PINTENT = "pendingIntent"
        val PARAM_RESULT = "result"

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