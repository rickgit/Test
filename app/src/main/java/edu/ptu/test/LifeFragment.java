package edu.ptu.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ptu.utils.utils.ClockUtils;

public class LifeFragment extends Fragment {
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        ClockUtils.getInstance().printDiffTime();
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        ClockUtils.getInstance().printDiffTime();
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ClockUtils.getInstance().printDiffTime();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ClockUtils.getInstance().printDiffTime();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ClockUtils.getInstance().printDiffTime();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        ClockUtils.getInstance().printDiffTime();
        super.onStart();
    }

    @Override
    public void onResume() {
        ClockUtils.getInstance().printDiffTime();
        super.onResume();
    }

    @Override
    public void onPause() {
        ClockUtils.getInstance().printDiffTime();
        super.onPause();
    }

    @Override
    public void onStop() {
        ClockUtils.getInstance().printDiffTime();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        ClockUtils.getInstance().printDiffTime();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        ClockUtils.getInstance().printDiffTime();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        ClockUtils.getInstance().printDiffTime();
        super.onDetach();
    }


}
