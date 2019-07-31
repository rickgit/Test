package edu.ptu.test.utils;

import java.util.concurrent.TimeUnit;

public class ClockUtils {
    private static ClockUtils clockUtils = new ClockUtils();

    public static ClockUtils getInstance() {
        return clockUtils;
    }

    //-----------
    Clock clock = new ClockFactory().getMillisClock();
    long time = clock.getCurrentTime();

    private ClockUtils() {

    }

    private void printDiffTime(String pre) {
        long diff = clock.getCurrentTime() - time;

        if (diff > clock.getDiff())
            System.out.println(".");
        System.out.println(pre + diff + clock.getunit());

        time = clock.getCurrentTime();
    }

    public void printDiffTime() {
        String pre = "(" + Thread.currentThread().getStackTrace()[3].getClassName() + "#" +
                Thread.currentThread().getStackTrace()[3].getMethodName() + "," +
                Thread.currentThread().getStackTrace()[3].getLineNumber() + ")";
        printDiffTime(pre + ": ");
    }

    //------------
    public static class ClockFactory {
        public Clock getMillisClock() {
            return new MillisClock();
        }

        public Clock getNanoClock() {
            return new NanoClock();
        }
    }

    static interface Clock {
        public long getCurrentTime();

        public long getDiff();

        public String getunit();
    }

    public static class MillisClock implements Clock {
        public long getCurrentTime() {
            return System.currentTimeMillis();
        }

        public long getDiff() {
            return TimeUnit.MILLISECONDS.toMillis(200);
        }

        @Override
        public String getunit() {
            return "ms";
        }
    }

    public static class NanoClock implements Clock {
        public long getCurrentTime() {
            return System.nanoTime();
        }

        public long getDiff() {
            return TimeUnit.MILLISECONDS.toNanos(200);
        }

        @Override
        public String getunit() {
            return "ns";
        }
    }
}
