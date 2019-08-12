package edu.ptu.test;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ptu.utils.utils.ClockUtils;

public class LifeFragment extends Fragment {
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        ClockUtils.getInstance(). printDiffTime(this,isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ClockUtils.getInstance(). printDiffTime(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onStart();
    }

    @Override
    public void onResume() {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onPause();
    }

    @Override
    public void onStop() {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        ClockUtils.getInstance(). printDiffTime(this);
        super.onDetach();
    }


}
