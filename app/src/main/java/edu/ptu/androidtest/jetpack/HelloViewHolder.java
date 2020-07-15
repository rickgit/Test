package edu.ptu.androidtest.jetpack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class HelloViewHolder extends AndroidViewModel  {
    public HelloViewHolder(@NonNull Application application) {
        super(application);
    }

    public void doSome(){
        MutableLiveData<Object> livedata = new MutableLiveData<>();

    }
}
