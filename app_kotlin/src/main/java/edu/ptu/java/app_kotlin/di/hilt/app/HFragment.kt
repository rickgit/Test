package edu.ptu.java.app_kotlin.di.hilt.app

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import edu.ptu.java.app_kotlin.di.hilt.app.viewmodel.HViewModel

@AndroidEntryPoint
class HFragment : Fragment(){
    private val viewModel by viewModels<HViewModel>()
    fun getViewModel(): ViewModel {
        return viewModel
    }
}