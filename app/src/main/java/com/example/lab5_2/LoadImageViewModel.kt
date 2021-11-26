package com.example.lab5_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LoadImageViewModel: ViewModel() {
    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap : LiveData<Bitmap> = _bitmap
    private lateinit var executorService: ExecutorService

    fun downloadImage(){
        executorService = Executors.newFixedThreadPool(1)
        executorService.execute {
            val url = URL("https://hinhanhdephd.com/wp-content/uploads/2021/02/hinh-8-3-cho-me-2.jpg")
            val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            _bitmap.postValue(image)
        }
    }

    override fun onCleared() {
        super.onCleared()
        executorService.shutdown()
    }
}