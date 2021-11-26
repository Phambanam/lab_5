package com.example.lab5_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab5_2.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         Picasso.get()
             .load("https://hinhanhdephd.com/wp-content/uploads/2021/02/hinh-8-3-cho-me-2.jpg")
             .placeholder(R.drawable.img)
             .error(R.drawable.img_1)
             .into(binding.imageView)
    }


}