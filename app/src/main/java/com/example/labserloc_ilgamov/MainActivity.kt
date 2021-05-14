package com.example.labserloc_ilgamov

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.app.ActivityManager
import android.content.Context
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient

class MainActivity : AppCompatActivity() {

    //Локация
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val serviceClass = RandomNumberService::class.java
        val intent = Intent(applicationContext, serviceClass)

        // кнопка старт
        button_start.setOnClickListener{

            if (!isServiceRunning(serviceClass)) {

                startService(intent)
            } else {
                toast("Service already running.")
            }
        }
        // кнопка стоп
        button_stop.setOnClickListener{

            if (isServiceRunning(serviceClass)) {

                stopService(intent)
            } else {
                toast("Service already stopped.")
            }
        }
        // активность генерации чисел
        button_stats.setOnClickListener{
            if (isServiceRunning(serviceClass)) {
                toast("Сервис запущен.")
            } else {
                toast("Сервис остановлен.")
            }

        }
    }

    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager


        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {

                return true
            }
        }
        return false
    }
}

fun Context.toast(message:String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()


}