package edu.ptu.javatest._90_jcu._11_container.blockqueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

import edu.ptu.javatest._80_storage._80_file.classfile.RefectTest;

//LinkedBlockingQueue、ArrayBlockingQueue、DelayQueue、SynchronousQueue
public class BlockingQueueTest {

    @Test
    public void testLinkedBlockQueue() {//有缓存任务
        LinkedBlockingDeque<Runnable> synchronousQueue = new LinkedBlockingDeque<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {

                synchronousQueue.offer(() -> System.out.println(" 执行" + finalI));
            }).start();
        }
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                while (true) {

                    Runnable take = synchronousQueue.take();
                    take.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArrayBlockQueue() {//需要先take,在offer，才能获取到所有任务。Synchroneous不缓存任务
        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(3);
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(12);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bq.offer(() -> System.out.println(" 执行" + finalI));
            });
            thread.setName("(" + i + ")");
            thread.start();
        }
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                while (true) {

                    Runnable take = bq.take();
                    take.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread.setName("( take )");
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
