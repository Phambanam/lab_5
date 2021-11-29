package com.example.lab5_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LoadImageViewModel: ViewModel() {
    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap : LiveData<Bitmap> = _bitmap
    private var executorService: ExecutorService = Executors.newFixedThreadPool(1)
    fun downloadImage(url : String){
        executorService.execute {
            val image = BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
            _bitmap.postValue(image)
        }
        Log.i("LoadImage", "${ Thread.currentThread()}")
    }
    override fun onCleared() {
        executorService.shutdown()
        super.onCleared()

    }
}