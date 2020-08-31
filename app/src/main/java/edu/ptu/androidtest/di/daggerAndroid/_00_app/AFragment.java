package edu.ptu.androidtest.di.daggerAndroid._00_app;

import android.app.Activity;
import android.app.Fragment;


import javax.inject.Inject;

import dagger.android.AndroidInjection;
@Deprecated //不支持注解 androidx.fragment.app.Fragment
public class AFragment extends Fragment {
    @Inject
    String someDep;

    @Override
    public void onAttach(Activity activity) {
        AndroidInjection.inject(this);
        super.onAttach(activity);
    }
}