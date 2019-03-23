package com.android.killqwerty.myapp.myapp3.android_2

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class Lesson4Service : Service() {
    override fun onCreate() {
        Log.d(Lesson4forService.MYTAG, "onCreate started")
        myJob = GlobalScope.launch { myFun() }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent?.getIntExtra(Lesson4forService.PARAM_TIME,0)
        val pi = intent?.getParcelableExtra<PendingIntent>(Lesson4forService.PARAM_PINTENT)
        if (!myJob.isActive) { // надо будет поколдовать чет не понял чутка
            myJob.start()
        }
        else {
            GlobalScope.launch { newJob(time,startId,pi) }.start()
            Log.d(Lesson4forService.MYTAG, "onStartCommand started")
        }
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        Log.d(Lesson4forService.MYTAG, "onDestroy service")

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
            Log.d(Lesson4forService.MYTAG, "$count")
            if (count % 10 == 0)
                Log.d(Lesson4forService.MYTAG,"----------------------------------------------------------------")
            if (count % 100 == 0) {
                stopSelf()
                myJob.cancel()
            }

        }
    }

    suspend fun newJob(time: Int? ,startId : Int,pi : PendingIntent?){
        Log.d("MYTAG","вошел в метод newJob time = $time pi = $pi startid = $startId ")
        pi?.send(Lesson4forService.STATUS_START)
        if (time != null) {
            delay((time * 1000).toLong())
        var myIntent = Intent().putExtra(Lesson4forService.PARAM_RESULT, time * 1000)
            pi?.send(this,Lesson4forService.STATUS_FINISH,myIntent)
            delay(3000)
            pi?.send(Lesson4forService.STATUS_CLEAR)
            stopSelf()}
    }

    companion object {
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