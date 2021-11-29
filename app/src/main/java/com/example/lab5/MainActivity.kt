package com.example.lab5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab5.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var backgroundThread: Thread
    private  var secondsElapsed: Int = 0
    private lateinit var sharedPreferences: SharedPreferences
    private var start = 0L
    private var end = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("SECONDS", Context.MODE_PRIVATE)

    }

    override fun onStart() {
        secondsElapsed = sharedPreferences.getInt("SECONDS", 0)
        start = System.currentTimeMillis()
        backgroundThread = Thread {
            try {
                while (!Thread.currentThread().isInterrupted) {
                    Log.d("TEST", "${Thread.currentThread()} is iterating")
                    binding.textSecondsElapsed.post {
                        val current =
                            secondsElapsed + ((System.currentTimeMillis() - start)/1000).toInt()
                        binding.textSecondsElapsed.text = getString(R.string.sec_elapsed, current)
                    }
                    Thread.sleep(1000)
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
        backgroundThread.start()
        Log.d("TEST","thread start\nSECONDS = $secondsElapsed ")
        super.onStart()
    }


    override fun onStop() {
        end = System.currentTimeMillis()
        backgroundThread.interrupt()
        secondsElapsed += ((end - start)/1000).toInt()
        sharedPreferences.edit().putInt("SECONDS", secondsElapsed).apply()
        Log.d("TEST","Thread interrupted\n" +
                "SECONDS = $secondsElapsed ")
        super.onStop()
    }

}