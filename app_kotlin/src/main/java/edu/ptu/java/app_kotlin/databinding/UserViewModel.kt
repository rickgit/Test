package edu.ptu.java.app_kotlin.databinding

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var u= MutableLiveData<String>()

    fun onClick(){
        u.value=u.value+"1"
    }
}