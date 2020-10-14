package edu.ptu.java.app_kotlin.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class UserBindable(): BaseObservable() {
    @Bindable
    var name = "初始化数据"
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    @Bindable
    var url = "https://tse1-mm.cn.bing.net/th/id/OIP.BW54Wc2X6zBd8PyKkXgDqgHaHa?pid=Api&rs=1"
        set(value) {
            field = value
            notifyPropertyChanged(BR.url)
        }
}