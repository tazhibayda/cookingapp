package com.example.cookingapp.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CookNowViewModel: ViewModel(){
    private var _score = MutableLiveData(0)
    val score: LiveData<Int> = _score

     fun increaseScore() {
        _score.value = score.value!! + 10
    }

     fun decreaseScore() {
         _score.value = score.value!! - 10
    }



}