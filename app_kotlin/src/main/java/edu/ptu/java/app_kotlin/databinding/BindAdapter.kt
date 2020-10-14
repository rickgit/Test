package edu.ptu.java.app_kotlin.databinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object DataBindingAdapter {

    @BindingAdapter("android:paddingLeft")
    fun setPaddingLeft(view: View, padding: Int) {
        println("paddingLeft")
        view.setPadding(padding,
                view.getPaddingTop(),
                view.getPaddingRight(),
                view.getPaddingBottom())
    }

}