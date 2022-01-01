package com.example.lab5_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class LoadImageViewModel : ViewModel() {
    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap> = _bitmap

    fun downloadImage() {
        viewModelScope.launch(Dispatchers.IO) {
            val url = URL("https://hinhanhdephd.com/wp-content/uploads/2021/02/hinh-8-3-cho-me-2.jpg")
            val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            withContext(Dispatchers.Main){ _bitmap.postValue(image)}

        }

    }


}