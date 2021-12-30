package com.example.lab5_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab5_2.databinding.ActivityMainBinding
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future


class MainActivity : AppCompatActivity() {
    private lateinit var future : Future<*>
    private lateinit var binding: ActivityMainBinding
    private fun downloadImage(executorService: ExecutorService, url : String) : Future<*> {

        return  executorService.submit {
            Log.i("LoadImage", "start download ${Thread.currentThread().name}")
          val image =   BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
            binding.imageView.post{
                binding.imageView.setImageBitmap(image)
            }
            if (image != null)
            Log.i("LoadImage", "finish download ${Thread.currentThread().name}")
        }
    }
    private val url ="https://hinh1anhdephd.com/wp-content/uploads/2021/02/hinh-8-3-cho-me-2.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        Log.i("TEST", "Number of threads: " + Thread.getAllStackTraces().size)
        setContentView(binding.root)
        future = downloadImage((applicationContext as MyApplication).executorService,url)
    }

    override fun onDestroy() {
        super.onDestroy()
        future.cancel(true)
    }
}
