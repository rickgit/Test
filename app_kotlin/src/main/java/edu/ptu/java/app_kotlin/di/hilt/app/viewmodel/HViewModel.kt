package edu.ptu.java.app_kotlin.di.hilt.app.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import edu.ptu.java.app_kotlin.di.hilt.module.other.HDatabaseModule

class HViewModel @ViewModelInject constructor(var injectApp:String,var db: HDatabaseModule.RoomRepository) :ViewModel(){
}