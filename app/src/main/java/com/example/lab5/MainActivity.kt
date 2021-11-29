package com.example.lab5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.lab5.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var secondsElapsed: Int = 0
    private lateinit var sharedPreferences: SharedPreferences
    private var start = 0L
    private var end = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("SECONDS", Context.MODE_PRIVATE)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.d("TEST", "Coroutine is launching")
                while (true) {
                    Log.d("TEST", "coroutine is working ")
                    binding.textSecondsElapsed.post {
                        val current =
                            secondsElapsed + ((System.currentTimeMillis() - start) / 1000).toInt()
                        binding.textSecondsElapsed.text = getString(R.string.sec_elapsed, current)
                    }
                    delay(1000)
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        start = System.currentTimeMillis()
        secondsElapsed = sharedPreferences.getInt("SECONDS", 0)
        Log.d("TEST", "thread start\nSECONDS = $secondsElapsed ")
    }

    override fun onStop() {
        super.onStop()
        end = System.currentTimeMillis()
        secondsElapsed += ((end - start) / 1000).toInt()
        sharedPreferences.edit().putInt("SECONDS", secondsElapsed).apply()
        Log.d(
            "TEST", "Thread interrupted\n" +
                    "SECONDS = $secondsElapsed "
        )
    }
}