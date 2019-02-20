package com.android.killqwerty.myapp.myapp3.android_2

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log

class Lesson3_MyService : Service(){
    override fun onCreate() {
        Log.d(MYTAG,"onCreate started")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(MYTAG,"onStartCommand started")
        someFun()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
    Log.d(MYTAG,"onDestroy service")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun someFun() {
        Log.d(MYTAG,"someFun start")
        MyAsync().execute()
        MyAsync().execute()
    }

    companion object {
        val MYTAG = "MYTAG"
    }
}
class MyAsync : AsyncTask<Void,Void,Unit>(){
    override fun doInBackground(vararg p0: Void?) {
        for (x in 0..100) {
            Log.d("MYTAG", "-$x-")
            Thread.sleep(10)
        }
    }

    override fun onPostExecute(result: Unit?) {
       // Lesson3_MyService().onDestroy()
        Log.d("MYTAG","finish!")
        super.onPostExecute(result)
    }
}