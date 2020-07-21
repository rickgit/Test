package edu.ptu.javatest._90_jcu._10_base._14_sync;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static edu.ptu.javatest._90_jcu._10_base._14_sync._62_aqs_lock.printAqs;
import static edu.ptu.javatest._90_jcu._10_base._14_sync._62_aqs_lock.printNode;

public class _63_aqs_node_waitstate {

    //condition 的node是waitstatus -2
    //lock 的node是waitstatus 0,-1
    @Test
    public void testWaitState() {
        ReentrantLock reentrantLock = new ReentrantLock(true);//默认非公平锁，打印日志 headObj.next
        Condition condition = reentrantLock.newCondition();
        Object lockObj = null;
        try {

            Field lock = ReentrantLock.class.getDeclaredField("sync");
            lock.setAccessible(true);
            lockObj = lock.get(reentrantLock);

            for (int i = 0; i < 3; i++) {
                Object finalLockObj = lockObj;
                Thread thread = new Thread(() -> {
                    try {
                        reentrantLock.lock();
                        printConditionNode(condition);
                        condition.await();
                        printConditionNode(condition);
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printAqs(lockObj);
        try {
            reentrantLock.lock();
            printConditionNode(condition);
            System.out.println("开始 signal");
            condition.signalAll();
            printAqs(lockObj);
        } finally {
            reentrantLock.unlock();

        }
//        printAqs(lockObj);
//        printConditionNode(condition);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //node转移 conditionA->AQS->ConditionB->AQS
    @Test
    public void testWaitStateB() {
        ReentrantLock reentrantLock = new ReentrantLock(true);//默认非公平锁，打印日志 headObj.next
        Condition conditionA = reentrantLock.newCondition();
        Condition conditionB = reentrantLock.newCondition();
        Object lockObj = null;
        try {

            Field lock = ReentrantLock.class.getDeclaredField("sync");
            lock.setAccessible(true);
            lockObj = lock.get(reentrantLock);

            for (int i = 0; i < 3; i++) {
                Object finalLockObj = lockObj;
                Thread thread = new Thread(() -> {
                    try {
                        reentrantLock.lock();
                        conditionA.await();
                        System.out.println("进入conditionB");
                        conditionB.await();
                        System.out.println("执行完成");
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("未signal");
        printAqs(lockObj);
        try {
            reentrantLock.lock();
            printConditionNode(conditionA);
            System.out.println("开始 signalA");
            conditionA.signalAll();
            printAqs(lockObj);


        } finally {
            reentrantLock.unlock();

        }
        try {
            reentrantLock.lock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printConditionNode(conditionB);
            System.out.println("开始 signalB");

            conditionB.signalAll();//不能和conditionA在同一个lock块里面
        }finally {
            reentrantLock.unlock();
        }
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printConditionNode(Object object) {

        try {
            synchronized (object) {
                System.out.println();
                Field firstWaiter = object.getClass().getDeclaredField("firstWaiter");
                Field lastWaiter = object.getClass().getDeclaredField("lastWaiter");
                System.out.println("condition" + object.toString().substring(object.getClass().getName().length()));
                firstWaiter.setAccessible(true);
                Object nodeIterObj = firstWaiter.get(object);
//                firstWaiter.setAccessible(true);
//                nodeIterObj = firstWaiter.get(object);

                while (nodeIterObj != null) {
                    printNode(nodeIterObj, "nextWaiter->", false);
//                    Field prev = nodeIterObj.getClass().getDeclaredField("prev");//
                    Field next = nodeIterObj.getClass().getDeclaredField("next");//
                    Field nextWaiter = nodeIterObj.getClass().getDeclaredField("nextWaiter");
                    nextWaiter.setAccessible(true);
                    nodeIterObj = nextWaiter.get(nodeIterObj);

                }

                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
