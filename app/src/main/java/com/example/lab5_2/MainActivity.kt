package com.example.lab5_2

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.lab5_2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


class MainActivity : AppCompatActivity() {
    private val viewModel = LoadImageViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("Test",Thread.currentThread().name)
            val url = URL("https://hinhanhdephd.com/wp-content/uploads/2021/02/hinh-8-3-cho-me-2.jpg")
            val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            withContext(Dispatchers.Main){
                Log.d("Test",Thread.currentThread().name)
                binding.imageView.setImageBitmap(image)}

        }
        /*viewModel.downloadImage()
        viewModel.bitmap.observe(this) { bitmap ->
            binding.imageView.setImageBitmap(bitmap)
        }*/
    }


}