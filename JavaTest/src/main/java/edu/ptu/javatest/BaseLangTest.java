package edu.ptu.javatest;

import org.junit.Test;

import edu.ptu.utils.utils.ClockUtils;

public class BaseLangTest {
    @Test
    public void testFinnaly() {
        try {
            ClockUtils.getInstance().printDiffTime();
            boolean isHasException = false;
            if (isHasException)
                throw new Exception("some exception");
            else return;

        } catch (Exception e) {
            ClockUtils.getInstance().printDiffTime();
            e.printStackTrace();
        } finally {
            ClockUtils.getInstance().printDiffTime();
        }
        ClockUtils.getInstance().printDiffTime();
    }

}
