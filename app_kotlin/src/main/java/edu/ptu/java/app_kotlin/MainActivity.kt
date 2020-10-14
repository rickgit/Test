package edu.ptu.java.app_kotlin

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import edu.ptu.java.app_kotlin.databinding.ActivityMainBinding
import edu.ptu.java.app_kotlin.databinding.UserBindable
import edu.ptu.java.app_kotlin.databinding.UserViewModel
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
//        val viewBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        viewBinding.user = UserBindable()//@Bindable
        viewBinding.userVm = UserViewModel();//LiveData
        viewBinding.lifecycleOwner=this//必须要加这句，才能使用LiveData MutableLiveData<Int>().observe(this, Observer {  })
        setContentView(viewBinding.root)
        thread {
            Thread.sleep(3000)
            viewBinding.user?.name = "已加载数据"//var修饰，必须安全判断

            viewBinding.textview.post {
                viewBinding.textview?.setPadding(3);
            }
         }

    }

}
