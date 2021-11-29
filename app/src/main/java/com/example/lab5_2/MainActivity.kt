package com.example.lab5_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab5_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val viewModel = LoadImageViewModel()
    private val url ="https://hinhanhdephd.com/wp-content/uploads/2021/02/hinh-8-3-cho-me-2.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.downloadImage(url)
        viewModel.bitmap.observe(this) { bitmap ->
            binding.imageView.setImageBitmap(bitmap)
        }
    }


}