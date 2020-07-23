package edu.ptu.javatest._90_jcu._10_jsr133._16_sync;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class _62_aqs_lock {
    @Test
    public void testLockState() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {

            Field lock = ReentrantLock.class.getDeclaredField("sync");
            lock.setAccessible(true);
            Object lockObj = lock.get(reentrantLock);
            printAqs(lockObj);
            reentrantLock.lock();//每次lock状态加1
            try {
                reentrantLock.lock();
                printAqs(lockObj);//状态修改为2
            } finally {
                reentrantLock.unlock();//每次unlock状态减1
            }

            printAqs(lockObj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            reentrantLock.unlock();
        }
    }

    @Test
    public void testMultiThreadUnfairLock() {//headObj.next
        ReentrantLock reentrantLock = new ReentrantLock();//默认非公平锁，打印日志 headObj.next
        try {

            Field lock = ReentrantLock.class.getDeclaredField("sync");
            lock.setAccessible(true);
            Object lockObj = lock.get(reentrantLock);

            for (int i = 0; i < 10; i++) {
                Thread thread = new Thread(() -> {
                    try {

                        reentrantLock.lock();
                        Thread.sleep(300);

                        printAqs(lockObj);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        reentrantLock.unlock();
                    }
                });
                thread.setName("线程 -" + i + " ");
                thread.start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Thread.sleep(113000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiThreadFairLock() {
        ReentrantLock reentrantLock = new ReentrantLock(true);//默认非公平锁，打印日志 headObj.next
        Object lockObj=null;
        try {

            Field lock = ReentrantLock.class.getDeclaredField("sync");
            lock.setAccessible(true);
            lockObj = lock.get(reentrantLock);

            for (int i = 0; i < 3; i++) {
                Object finalLockObj = lockObj;
                Thread thread = new Thread(() -> {
                    try {
                        reentrantLock.lock();
                        Thread.sleep(1000);

                        printAqs(finalLockObj);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        reentrantLock.unlock();
                    }
                });
                thread.setName("t(" + i + ") ");
                thread.start();
            }
            printAqs(lockObj);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                Object finalLockObj = lockObj;
                Thread thread = new Thread(() -> {
                    try {
                        reentrantLock.lock();
                        Thread.sleep(1000);

                        printAqs(finalLockObj);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        reentrantLock.unlock();
                    }
                });
                thread.setName("t(" + i + ") ");
                thread.start();
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printAqs(lockObj);
    }

    public static void printAqs(Object lock) {
        try {

            Field state = AbstractQueuedSynchronizer.class.getDeclaredField("state");
            Field head = AbstractQueuedSynchronizer.class.getDeclaredField("head");
            Field tail = AbstractQueuedSynchronizer.class.getDeclaredField("tail");
            Field exclusiveOwnerThread = AbstractOwnableSynchronizer.class.getDeclaredField("exclusiveOwnerThread");

            synchronized (lock) {
                state.setAccessible(true);
                int stateObj = (int) state.get(lock);
                System.out.print("AQS state(" + stateObj + ")  ");
                exclusiveOwnerThread.setAccessible(true);
                Object exclusiveOwnerThreadObj = exclusiveOwnerThread.get(lock)==null?"null":((Thread)exclusiveOwnerThread.get(lock)).getName();
                System.out.print(" OwnerThread " + exclusiveOwnerThreadObj+ "  ");

                tail.setAccessible(true);
                Object tailObj = tail.get(lock);
//                printNode(tailObj,"tailNode",false);
//
//                head.setAccessible(true);
//                Object headObj = head.get(lock);
//                printNode(headObj,"headNode",true);
                System.out.println();
                Object objIter=tailObj;
                while (objIter != null) {
                    printNode(objIter," prev->",false);

//                    Field next = objIter.getClass().getDeclaredField("next");//
                    Field prev = objIter.getClass().getDeclaredField("prev");//
                    Field nextWaiter = objIter.getClass().getDeclaredField("nextWaiter");

                    prev.setAccessible(true);
                    objIter = prev.get(objIter);

                }


                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }


    }

    public static void printNode(Object nodeObj,String flag,boolean endNewLine)   {
        if (nodeObj == null)
            return;
        try {
            Field next = nodeObj.getClass().getDeclaredField("next");
            Field prev = nodeObj.getClass().getDeclaredField("prev");
            Field nextWaiter = nodeObj.getClass().getDeclaredField("nextWaiter");
            Field thread = nodeObj.getClass().getDeclaredField("thread");
            Field waitStatus = nodeObj.getClass().getDeclaredField("waitStatus");



            System.out.print(" "+flag+" Node" + nodeObj.toString().substring(nodeObj.getClass().getName().length()) + "");

            nextWaiter.setAccessible(true);
            Object nextWaiterObj = nextWaiter.get(nodeObj)==null?"null":nextWaiter.get(nodeObj).toString().substring(nextWaiter.get(nodeObj).getClass().getName().length());
            System.out.print("（nextWaiter " + nextWaiterObj + " ");

//            next.setAccessible(true);
//            Object nextObj = next.get(nodeObj)==null?"null":next.get(nodeObj).toString().substring(next.get(nodeObj).getClass().getName().length());
//            System.out.print(", next " + nextObj + " ");
//
//            prev.setAccessible(true);
//            Object prevObj = prev.get(nodeObj)==null?"null":prev.get(nodeObj).toString().substring(prev.get(nodeObj).getClass().getName().length());
//            System.out.print(", prev " + prevObj + "  ）");

            waitStatus.setAccessible(true);
            Object waitStatusObj = waitStatus.get(nodeObj)==null?"null": waitStatus.get(nodeObj);
            System.out.print(", waitStatusObj（" + waitStatusObj + "）");


            thread.setAccessible(true);
            Object threadObj = thread.get(nodeObj)==null?"null":(((Thread)thread.get(nodeObj)).getName());
            System.out.print(", thread " + threadObj + "  ）");

            if (endNewLine)
            System.out.println( );
        } catch ( Exception e) {
            e.printStackTrace();
        }

    }
}
