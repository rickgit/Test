package edu.ptu.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;

import edu.ptu.utils.utils.ClockUtils;

public class LifeActivity extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       ClockUtils.getInstance().printDiffTime();
        FrameLayout view = new FrameLayout(this);
        view.setBackgroundColor(0xff00c00c);
        setContentView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),LifeActivity.class));

            }
        });
    }

    @Override
    protected void onStart() {
        ClockUtils.getInstance().printDiffTime();
        super.onStart();
    }

    @Override
    protected void onResume() {
        ClockUtils.getInstance().printDiffTime();
        super.onResume();
    }

    @Override
    protected void onPause() {
        ClockUtils.getInstance().printDiffTime();
        super.onPause();
    }

    @Override
    protected void onStop() {
        ClockUtils.getInstance().printDiffTime();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        ClockUtils.getInstance().printDiffTime();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ClockUtils.getInstance().printDiffTime();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        ClockUtils.getInstance().printDiffTime();
        super.onRestoreInstanceState(savedInstanceState);
    }
}
