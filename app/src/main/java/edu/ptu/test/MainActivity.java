package edu.ptu.test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.gson.Gson;

/**
 * Created by anshu.wang on 2016/12/15.
 */

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

    }
}
