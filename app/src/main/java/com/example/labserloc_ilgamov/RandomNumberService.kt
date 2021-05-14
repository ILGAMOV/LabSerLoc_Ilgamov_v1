package com.example.labserloc_ilgamov

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import java.util.*


class RandomNumberService : Service() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        toast("Сервис запущен.")

        mHandler = Handler()
        mRunnable = Runnable { showRandomNumber() }
        mHandler.postDelayed(mRunnable, 5000) //милисек

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Сервис отключен.")
        mHandler.removeCallbacks(mRunnable)
    }

    private fun showRandomNumber() {
        val rand = Random()
        val number = rand.nextInt(100)
        toast("Генерация рандомного числа сервисом активна. Рандомное число: $number")
        mHandler.postDelayed(mRunnable, 5000)
    }
}