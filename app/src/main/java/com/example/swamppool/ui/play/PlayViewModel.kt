package com.example.swamppool.ui.play

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to Swamp Pool"
    }
    val text: LiveData<String> = _text
}
