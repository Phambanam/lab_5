package com.example.lab5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.lab5.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  var secondsElapsed: Int = 0
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("SECONDS", Context.MODE_PRIVATE)

        lifecycleScope.launchWhenStarted {
            Log.d("TEST", "Coroutine is launching")
            while(true){
                Log.d("TEST", "coroutine is working ")
                binding.textSecondsElapsed.post {
                    binding.textSecondsElapsed.text = getString(R.string.sec_elapsed, secondsElapsed++)
                }
                delay(1000)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        secondsElapsed = sharedPreferences.getInt("SECONDS", 0)
        Log.d("TEST","thread start\nSECONDS = $secondsElapsed ")
    }

    override fun onStop() {
        super.onStop()
        sharedPreferences.edit().putInt("SECONDS", secondsElapsed).apply()
        Log.d("TEST","Thread interrupted\n" +
                "SECONDS = $secondsElapsed ")
    }
}