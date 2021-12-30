package com.example.lab5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab5.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var secondsElapsed: Int = 0
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var future: Future<*>
    private fun startCountTime(executorService: ExecutorService): Future<*> {
        return executorService.submit {
            while (!executorService.isShutdown) {
                Log.d("TEST", "${Thread.currentThread()} is iterating")
                binding.textSecondsElapsed.post {
                    binding.textSecondsElapsed.text =
                        getString(R.string.sec_elapsed, secondsElapsed++)
                }
                Thread.sleep(1000)
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("SECONDS", Context.MODE_PRIVATE)
    }

    override fun onStart() {
        super.onStart()
        future = startCountTime((applicationContext as MyApplication).executorService)
        secondsElapsed = sharedPreferences.getInt("SECONDS", 0)
        Log.d("TEST", "thread start\nSECONDS = $secondsElapsed ")
    }

    override fun onStop() {
        super.onStop()
        future.cancel(true)
        sharedPreferences.edit().putInt("SECONDS", secondsElapsed).apply()
        Log.d(
            "TEST", "Thread interrupted\n" +
                    "SECONDS = $secondsElapsed "
        )
    }
}