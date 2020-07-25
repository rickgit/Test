package edu.ptu.androidtest._60_ui;

import android.view.MotionEvent;
import android.view.View;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class TouchTest {
    @Test
    public void test(){
        View view = new View(InstrumentationRegistry.getInstrumentation().getTargetContext());
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
