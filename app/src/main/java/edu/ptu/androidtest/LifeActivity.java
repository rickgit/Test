package edu.ptu.androidtest;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.FrameLayout;

import edu.ptu.androidtest.jetpack.HelloViewHolder;
import edu.ptu.utils.utils.ClockUtils;

public class LifeActivity extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClockUtils.getInstance().printDiffTime(this);
        FrameLayout view = new FrameLayout(this);
        view.setBackgroundColor(0xff00c00c);
        setContentView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FragmentLifeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        new ViewModelProvider(this).get(  HelloViewHolder.class);
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
    protected void onRestart() {
        ClockUtils.getInstance().printDiffTime();
        super.onRestart();

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        ClockUtils.getInstance().printDiffTime();
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ClockUtils.getInstance().printDiffTime(this);
    }
}
