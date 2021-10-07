package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm._11_volatile;

public class VolatileVisiableTest {
    public volatile boolean mStop;
    public void dowork(){
        while (!mStop){
            visiableTest.mStop=false;
        }
    }
    public void setStop(){
        System.out.println("switch stop.........");
        mStop=true;
    }

    public static void main(String[] args) {
        VolatileVisiableTest visiableTest = new VolatileVisiableTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
              visiableTest.dowork();
                System.out.println("stop  .............");//
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                visiableTest.setStop();
            }
        }).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}