package edu.ptu.androidtest.jetpack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import edu.ptu.utils.utils.ClockUtils;

public class HelloViewHolder extends AndroidViewModel  {
    public HelloViewHolder(Application application,
                           SavedStateHandle handle) {
        super(application);
        System.out.println(this.toString());ClockUtils.getInstance().printDiffTime();
    }
    public HelloViewHolder(@NonNull Application application) {
        super(application);
    }//17d168f

    public void doSome(){
        MutableLiveData<Object> livedata = new MutableLiveData<>();

    }
}
