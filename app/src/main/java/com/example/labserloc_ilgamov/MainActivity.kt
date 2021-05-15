package com.example.labserloc_ilgamov

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.app.ActivityManager
import android.content.Context
import android.widget.Toast
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {
// ЛОКАЦИЯ
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// ЛОКАЦИЯ
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        findViewById<Button>(R.id.btn_get_location).setOnClickListener {
            fetchLocation()
        }
//

        val serviceClass = RandomNumberService::class.java
        val intent = Intent(applicationContext, serviceClass)

// СЕРВИС
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
                toast("Сервис остановлен.")
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
//

// ЛОКАЦИЯ
    private fun fetchLocation()
    {
        val task = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        task.addOnSuccessListener {
            if(it !=null){
                Toast.makeText(applicationContext, "${it.latitude} ${it.longitude}", Toast.LENGTH_SHORT).show()
            }
    }
    }
//

// СЕРВИС
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