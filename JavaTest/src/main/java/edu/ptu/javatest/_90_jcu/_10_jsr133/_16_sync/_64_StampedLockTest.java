package edu.ptu.javatest._90_jcu._10_jsr133._16_sync;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.locks.StampedLock;

import static edu.ptu.javatest._90_jcu._10_jsr133._16_sync._62_aqs_lock.printNode;

public class _64_StampedLockTest {

    @Test
    @SuppressWarnings("all")
    public void testReadStampedLock() {

        StampedLock stampedLock = new StampedLock();
        for (int i = 0; i < 11; i++) {
            Thread stamp = new Thread(new Runnable() {
                @Override
                public void run() {
                    long stamp = stampedLock.tryOptimisticRead();//乐观锁
                    if (!stampedLock.validate(stamp)) {//判断是否被其他线程修改过，若有，使用悲观锁
                        stamp = stampedLock.readLock();//悲观锁
                        try {
                            printAqs(stampedLock, "readstamp sleep ");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            stampedLock.unlockRead(stamp);
                        }
                    } else {
                        printAqs(stampedLock, "readstamp sleep ");
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        printAqs(stampedLock, "readstamp  sleep end");

                    }
                }
            });
            stamp.setName("线程(" + i + ")");
            stamp.start();
        }

        System.out.println();

    }

    @Test
    @SuppressWarnings("all")
    public void testWriteStampedLock() {
        StampedLock stampedLock = new StampedLock();

        for (int i = 10; i < 15; i++) {
            Thread stamp = new Thread(new Runnable() {
                @Override
                public void run() {
                    long stamp = stampedLock.writeLock();
                    try {
                        printAqs(stampedLock, "writestamp sleep");
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        printAqs(stampedLock, "writestamp sleep end");
                    } finally {
                        stampedLock.unlockWrite(stamp);
                    }
                }
            });
            stamp.setName("线程(" + i + ")");
            stamp.start();
        }
        System.out.println();

    }

    @Test
    @SuppressWarnings("all")
    public void testRead2WriteStampedLock() {

        StampedLock stampedLock = new StampedLock();
        for (int i = 0; i < 1; i++) {
            Thread stamp = new Thread(new Runnable() {
                @Override
                public void run() {
                    long stamp = stampedLock.tryOptimisticRead();//乐观锁
                    if (!stampedLock.validate(stamp)) {//判断是否被其他线程修改过，若有，使用悲观锁
                        stamp = stampedLock.readLock();//悲观锁
                        try {
                            printAqs(stampedLock, "readstamp sleep ");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            stampedLock.unlockRead(stamp);
                        }
                    } else {
                        printAqs(stampedLock, "readstamp sleep ");

                        long wstamp = stampedLock.tryConvertToWriteLock(stamp);
                        try {
                            printAqs(stampedLock, "writestamp ");
                        } finally {
                            stampedLock.unlockWrite(wstamp);
                        }
                        printAqs(stampedLock, "writestamp  unlocked");

                    }
                }
            });
            stamp.setName("线程(" + i + ")");
            stamp.start();
        }
        System.out.println();
    }

    @Test
    @SuppressWarnings("all")
    public void testWrite2ReadStampedLock() {
        StampedLock stampedLock = new StampedLock();

        for (int i = 10; i < 11; i++) {
            Thread stamp = new Thread(new Runnable() {
                @Override
                public void run() {
                    long stamp = stampedLock.writeLock();
                    try {
                        printAqs(stampedLock, "writestamp sleep");
                        long rStamp = stampedLock.tryConvertToReadLock(stamp);
                        try {printAqs(stampedLock, "readstamp  ");
                        } finally {
                            stampedLock.unlockRead(rStamp);
                            printAqs(stampedLock, "readstamp unlockRead");
                            stamp=stampedLock.writeLock();//重新获得写锁
                            printAqs(stampedLock, "writestamp re writeLock");
                        }

                    } finally {
                        stampedLock.unlockWrite(stamp);
                    }
                }
            });
            stamp.setName("线程(" + i + ")");
            stamp.start();
        }


        try {
            Thread.sleep(113000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    @SuppressWarnings("all")
    public void testStampedLock() {
        StampedLock stampedLock = new StampedLock();
        for (int i = 0; i < 1; i++) {
            Thread stamp = new Thread(new Runnable() {
                @Override
                public void run() {
                    long stamp = stampedLock.tryOptimisticRead();//乐观锁
                    if (!stampedLock.validate(stamp)) {//判断是否被其他线程修改过，若有，使用悲观锁
                        stamp = stampedLock.readLock();//悲观锁
                        try {
                            printAqs(stampedLock, "readstamp sleep ");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            stampedLock.unlockRead(stamp);
                        }
                    } else {
                        printAqs(stampedLock, "readstamp sleep ");
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        printAqs(stampedLock, "readstamp  sleep end");
                    }
                }
            });
            stamp.setName("线程(" + i + ")");
            stamp.start();
        }
        for (int i = 10; i < 13; i++) {
            Thread stamp = new Thread(new Runnable() {
                @Override
                public void run() {
                    long stamp = stampedLock.writeLock();
                    try {
                        printAqs(stampedLock, "writestamp sleep");
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        printAqs(stampedLock, "writestamp sleep end");
                    } finally {
                        stampedLock.unlockWrite(stamp);
                    }
                }
            });
            stamp.setName("线程(" + i + ")");
            stamp.start();
        }


        try {
            Thread.sleep(113000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void printAqs(Object lock, String label) {
        if (lock == null)
            return;
//        System.out.print(lock.toString().substring(lock.getClass().getName().length())+" ");
        try {

            Field state = lock.getClass().getDeclaredField("state");
            Field whead = lock.getClass().getDeclaredField("whead");
            Field wtail = lock.getClass().getDeclaredField("wtail");
            Field readerOverflow = lock.getClass().getDeclaredField("readerOverflow");
            Field readLockView = lock.getClass().getDeclaredField("readLockView");
            Field writeLockView = lock.getClass().getDeclaredField("writeLockView");
            Field readWriteLockView = lock.getClass().getDeclaredField("readWriteLockView");
            readerOverflow.setAccessible(true);
            readLockView.setAccessible(true);
            writeLockView.setAccessible(true);
            readWriteLockView.setAccessible(true);

            synchronized (lock) {
                state.setAccessible(true);
                long stateObj = (long) state.get(lock);
                System.out.print("Sync@" + label + " state(" + Long.toBinaryString(stateObj) + ")  ");
                System.out.print(" readerOverflow " + readerOverflow.get(lock));
                System.out.print(" readLockView " + readLockView.get(lock));
                System.out.print(" writeLockView " + writeLockView.get(lock));
                System.out.print(" readWriteLockView " + readWriteLockView.get(lock));


                wtail.setAccessible(true);
                Object tailObj = wtail.get(lock);
//                printNode(tailObj,"tailNode",false);
//
//                head.setAccessible(true);
//                Object headObj = head.get(lock);
//                printNode(headObj,"headNode",true);
                System.out.println();
                Object objIter = tailObj;
                while (objIter != null) {
                    printNode(objIter, " prev->", false);

//                    Field next = objIter.getClass().getDeclaredField("next");//
                    Field prev = objIter.getClass().getDeclaredField("prev");//

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

}
