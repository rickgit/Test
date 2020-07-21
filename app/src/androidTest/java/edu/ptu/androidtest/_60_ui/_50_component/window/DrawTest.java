package edu.ptu.androidtest._60_ui._50_component.window;

import android.view.View;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;

public class DrawTest {
    public void testDraw(){
        new View(InstrumentationRegistry.getInstrumentation().getTargetContext()).invalidate();
        new View(InstrumentationRegistry.getInstrumentation().getTargetContext()).postInvalidate();
        new TextView(InstrumentationRegistry.getInstrumentation().getTargetContext()).setText("");
    }
}
