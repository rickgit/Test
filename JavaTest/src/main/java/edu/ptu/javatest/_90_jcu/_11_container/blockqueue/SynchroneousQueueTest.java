package edu.ptu.javatest._90_jcu._11_container.blockqueue;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

import edu.ptu.javatest._20_ooad._50_dynamic._00_ReflectionTest;

public class SynchroneousQueueTest {
    @Test
    public void testSynchroneousQueueOffer() {//需要先take,在offer，才能获取到所有任务。Synchroneous不缓存任务
        SynchronousQueue<Runnable> synchronousQueue = new SynchronousQueue<>();
        boolean offer = synchronousQueue.offer(new Runnable() {
            @Override
            public void run() {

            }
        });
        Assert.assertFalse(offer);//必须有个take
    }

    @Test
    public void testSynchroneousQueueOffer2() {//需要先take,在offer，才能获取到所有任务。Synchroneous不缓存任务
        SynchronousQueue<Runnable> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            while (true) {
                try {
                    Runnable take = synchronousQueue.take();
                    take.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        boolean offer = synchronousQueue.offer(new Runnable() {
            @Override
            public void run() {
                System.out.println("runn ");
            }
        });
        Assert.assertTrue(offer);//必须有个take
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnfairSynchroneousQueue() {//需要先take,在offer，才能获取到所有任务。Synchroneous不缓存任务
        SynchronousQueue<Runnable> synchronousQueue = new SynchronousQueue<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(111);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronousQueue.offer(() -> System.out.println(" 执行" + finalI));
                printStackNode(synchronousQueue);
            });
            thread.setName("(" + i + ")");
            thread.start();
        }
        Thread thread = new Thread(() -> {
            try {
//                Thread.sleep(1000);
                while (true) {

                    Runnable take = synchronousQueue.take();
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
        printStackNode(synchronousQueue);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testfairSynchroneousQueue() {//需要先take,在offer，才能获取到所有任务。Synchroneous不缓存任务
        SynchronousQueue<Runnable> synchronousQueue = new SynchronousQueue<>(true);
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(12);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronousQueue.offer(() -> System.out.println(" 执行" + finalI));
            });
            thread.setName("(" + i + ")");
            thread.start();
        }
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                while (true) {

                    Runnable take = synchronousQueue.take();
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
        printQueueNode(synchronousQueue);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printQueueNode(Object object) {
        synchronized (object) {
            Object transferer = _00_ReflectionTest.getRefFieldObj(object, SynchronousQueue.class, "transferer");
            Object headNode = _00_ReflectionTest.getRefFieldObj(transferer, transferer.getClass(), "head");
            while (headNode != null) {
                Object matchNode = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "isData");
                Object waiterThread = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "waiter");
                Object itemObj = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "item");

                System.out.print("Node " + headNode.toString().substring(headNode.getClass().getName().length()));
                System.out.print("( waiterThread:" + (waiterThread == null ? "null " : ((Thread) waiterThread).getName()));
                System.out.print(" itemObj:" + itemObj);
                System.out.print(" matchNode:" + matchNode);
                System.out.print(" ) ");
                System.out.print(" -> ");

                headNode = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "next");
            }
            System.out.println();
        }

    }

    public void printStackNode(Object object) {
        synchronized (object) {
            Object transferer = _00_ReflectionTest.getRefFieldObj(object, SynchronousQueue.class, "transferer");
            Object headNode = _00_ReflectionTest.getRefFieldObj(transferer, transferer.getClass(), "head");

            while (headNode != null) {
                Object matchNode = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "match");
                Object waiterThread = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "waiter");
                Object itemObj = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "item");
                Object mode = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "mode");

                System.out.print("Node " + headNode.toString().substring(headNode.getClass().getName().length()));
                System.out.print("( waiterThread:" + (waiterThread == null ? "null " : ((Thread) waiterThread).getName()));
                System.out.print(" itemObj:" + itemObj);
                System.out.print(" matchNode:" + matchNode);
                System.out.print(" mode:" + mode);
                System.out.print(" ) ->");

                headNode = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "next");
            }
            System.out.println();
        }
    }

}
