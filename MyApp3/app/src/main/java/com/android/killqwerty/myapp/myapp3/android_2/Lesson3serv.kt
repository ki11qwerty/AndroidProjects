package com.android.killqwerty.myapp.myapp3.android_2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.android.killqwerty.myapp.myapp3.R
import kotlinx.android.synthetic.main.android2_lesson3serv.*

class Lesson3serv : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson3serv)
        setMyViews()
    }

    fun setMyViews() {
        andr2_lesson3_btn_exit.setOnClickListener { finish() }
        andr2_les3_btn_start.setOnClickListener { onClick(it) }
        andr2_les3_btn_stop.setOnClickListener { onClick(it) }

        andr2_les3_btn_launch1.setOnClickListener { onClick(it) }
        andr2_les3_btn_launch2.setOnClickListener { onClick(it) }
        andr2_les3_btn_launch3.setOnClickListener { onClick(it) }
    }

    fun onClick(view: View) {
        when (view.id) {
            andr2_les3_btn_start.id -> {
                startService(Intent(this, Lesson3Service::class.java))
                Toast.makeText(this, "Всю движуху смотреть в логах, \n тут ничего не видно =)", Toast.LENGTH_LONG).show()
            }
            andr2_les3_btn_stop.id -> stopService(Intent(this, Lesson3Service::class.java))
            andr2_les3_btn_launch1.id -> {
                val pi = createPendingResult(TASK_1, Intent(), 0)
                val intent = Intent(this, Lesson3Service::class.java)
                        .putExtra(PARAM_TIME, 7)
                        .putExtra(PARAM_PINTENT, pi)
                startService(intent)
            }
            andr2_les3_btn_launch2.id -> {
                val pi = createPendingResult(TASK_2, Intent(), 0)
                val intent = Intent(this, Lesson3Service::class.java)
                        .putExtra(PARAM_TIME, 4)
                        .putExtra(PARAM_PINTENT, pi)
                startService(intent)
            }
            andr2_les3_btn_launch3.id -> {
                val pi = createPendingResult(TASK_3, Intent(), 0)
                val intent = Intent(this, Lesson3Service::class.java)
                        .putExtra(PARAM_TIME, 2)
                        .putExtra(PARAM_PINTENT, pi)
                startService(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == STATUS_START){
            when(requestCode){
                TASK_1 -> {
                    andr2_les3_tv_status1.text = "Start"
                    andr2_les3_tv_status1.setTextColor(resources.getColor(R.color.colorGreen))}
                TASK_2 -> {
                    andr2_les3_tv_status2.text = "Start"
                    andr2_les3_tv_status2.setTextColor(resources.getColor(R.color.colorGreen)) }
                TASK_3 -> {
                    andr2_les3_tv_status3.text = "Start"
                    andr2_les3_tv_status3.setTextColor(resources.getColor(R.color.colorGreen))}
            }
        }
        if(resultCode == STATUS_FINISH){
            var result = data?.getIntExtra(PARAM_RESULT,0)
            when(requestCode){
                TASK_1 -> {andr2_les3_tv_status1.text = "Finish $result" }
                TASK_2 -> {andr2_les3_tv_status2.text = "Finish $result"}
                TASK_3 -> {andr2_les3_tv_status3.text = "Finish $result"}
            }
        }
        if(resultCode == STATUS_CLEAR ){
            when(requestCode){
                TASK_1 -> {
                    andr2_les3_tv_status1.text = "ожидание"
                    andr2_les3_tv_status1.setTextColor(resources.getColor(R.color.colorBlack))}
                TASK_2 -> {
                    andr2_les3_tv_status2.text = "ожидание"
                    andr2_les3_tv_status2.setTextColor(resources.getColor(R.color.colorBlack)) }
                TASK_3 -> {
                    andr2_les3_tv_status3.text = "ожидание"
                    andr2_les3_tv_status3.setTextColor(resources.getColor(R.color.colorBlack))}
            }
        }
    }

    companion object {
        val TASK_1 = 1
        val TASK_2 = 2
        val TASK_3 = 3
        val STATUS_START = 100
        val STATUS_FINISH = 200
        val STATUS_CLEAR = 300
        val PARAM_TIME = "time"
        val PARAM_PINTENT = "pendingIntent"
        val PARAM_RESULT = "result"
        val MYTAG = "MYTAG"
    }
}