package com.example.testcinema.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel (private var text: MutableLiveData<Int> = MutableLiveData(0)) : ViewModel() {

    fun getData(): LiveData<Int> {
        return text
    }

    fun setText(){
        text.value = text.value?.plus(1)
    }
}