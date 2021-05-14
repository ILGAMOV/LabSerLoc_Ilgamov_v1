package com.example.labserloc_ilgamov

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.app.ActivityManager
import android.content.Context
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

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
