package edu.ptu.java.app_kotlin.di.hilt.app

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import edu.ptu.java.app_kotlin.di.hilt.app.viewmodel.HViewModel

@AndroidEntryPoint
class HActivity:FragmentActivity(){
    private val viewModel by viewModels<HViewModel>()
    fun getViewModel():ViewModel{
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(HFragment(),"hlitFragment").commit()
    }
}