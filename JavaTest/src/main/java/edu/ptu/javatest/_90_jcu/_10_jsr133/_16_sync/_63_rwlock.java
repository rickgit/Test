package edu.ptu.javatest._90_jcu._10_jsr133._16_sync;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static edu.ptu.javatest._90_jcu._10_jsr133._16_sync._62_aqs_lock.printNode;

//1100001110010001000011001000111 threadlocal
public class _63_rwlock {
    @Test
    public void testConst() {
        int SHARED_UNIT = 16;
        System.out.println(Integer.toBinaryString((1 << 16)));
        ;//SHARED_UNIT

        int EXCLUSIVE_MASK = ((1 << 16) - 1);
        Assert.assertEquals(Integer.toBinaryString(EXCLUSIVE_MASK), ("1111111111111111"));
        Assert.assertEquals(Integer.toBinaryString(EXCLUSIVE_MASK).length(), 16);
    }

    //只允许一个线程写入，多个线程读取。默认非公平锁，先写的化，后面读线程等待
    //结果还有6个node
    @Test
    public void testWriteRead() throws Exception {
        Class<?> aClass = Class.forName("java.util.concurrent.locks.AbstractQueuedSynchronizer$Node");
        Field shared = aClass.getDeclaredField("SHARED");
        shared.setAccessible(true);
        Object o = shared.get(aClass);
        System.out.println("SHARED " + o.toString().substring(o.getClass().getName().length()));

        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();//默认非公平锁，打印日志 headObj.next
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object rwLock = null;
//                if (finalI % 2 == 0) {
                reentrantLock.readLock().lock();
//                } else {
//                    reentrantLock.writeLock().lock();
//                }
                try {
                    printReadWrite(reentrantLock);
                    Thread.sleep(3000);
                    printReadWrite(reentrantLock);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    if (finalI % 2 == 0) {
                    reentrantLock.readLock().unlock();
//                    } else {
//                        reentrantLock.writeLock().unlock();
//                    }
                }
            });
            thread.setName("t(" + i + ") ");
            thread.start();
        }
        for (int i = 10; i < 13; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Object rwLock = null;
//                if (finalI % 2 == 0) {
//                    reentrantLock.readLock().lock();
//                } else {
                reentrantLock.writeLock().lock();
//                }
                try {
                    printReadWrite(reentrantLock);
                    Thread.sleep(3000);
                    printReadWrite(reentrantLock);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    if (finalI % 2 == 0) {
//                        reentrantLock.readLock().unlock();
//                    } else {
                    reentrantLock.writeLock().unlock();
//                    }
                }
            });
            thread.setName("t(" + i + ") ");
            thread.start();
        }
        System.out.println();

        printReadWrite(reentrantLock);
        Thread.sleep(6000);
        printReadWrite(reentrantLock);

        Thread.sleep(7000);
    }

    //读数量在高16位，写在低16位
    @Test
    public void testReadWrite() throws Exception {
        Class<?> aClass = Class.forName("java.util.concurrent.locks.AbstractQueuedSynchronizer$Node");
        Field shared = aClass.getDeclaredField("SHARED");
        shared.setAccessible(true);
        Object o = shared.get(aClass);
        System.out.println("SHARED " + o.toString().substring(o.getClass().getName().length()));

        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock(true);//默认非公平锁，打印日志 headObj.next
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Object rwLock = null;
//                if (finalI % 2 == 0) {
                reentrantLock.readLock().lock();//先从缓存获取锁
//                } else {
//                    reentrantLock.writeLock().lock();
//                }
                try {
                    printReadWrite(reentrantLock);
                    Thread.sleep(3000);
                    printReadWrite(reentrantLock);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    if (finalI % 2 == 0) {
                    reentrantLock.readLock().unlock();
//                    } else {
//                        reentrantLock.writeLock().unlock();
//                    }
                }
            });
            thread.setName("t(" + i + ") ");
            thread.start();
        }
        for (int i = 10; i < 13; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object rwLock = null;
//                if (finalI % 2 == 0) {
//                    reentrantLock.readLock().lock();
//                } else {
                reentrantLock.writeLock().lock();
//                }
                try {
                    printReadWrite(reentrantLock);
                    Thread.sleep(3000);
                    printReadWrite(reentrantLock);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    if (finalI % 2 == 0) {
//                        reentrantLock.readLock().unlock();
//                    } else {
                    reentrantLock.writeLock().unlock();
//                    }
                }
            });
            thread.setName("t(" + i + ") ");
            thread.start();
        }
        System.out.println();

        printReadWrite(reentrantLock);
        Thread.sleep(300);
        printReadWrite(reentrantLock);

        Thread.sleep(3000);
    }

    @Test
    public void testReadLockConditionUnsupport() {
        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
        new Thread(() -> {
            ReentrantReadWriteLock.ReadLock readLock = reentrantLock.readLock();
            try {
                Condition condition = readLock.newCondition();
                Assert.fail();
            } catch (Exception e) {
                Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
            } finally {
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadLockConditionSupport() {
        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
        new Thread(() -> {
            ReentrantReadWriteLock.WriteLock writeLock = reentrantLock.writeLock();
            try {
                Condition condition = writeLock.newCondition();

            } catch (Exception e) {
                Assert.fail(e.getMessage());
            } finally {
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadToWriteLockIllegalMonitorState() {
        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
        new Thread(() -> {
            ReentrantReadWriteLock.WriteLock writeLock = reentrantLock.writeLock();
            ReentrantReadWriteLock.ReadLock readLock = reentrantLock.readLock();
            try {
                readLock.lock();
                try {
                    writeLock.tryLock(1, TimeUnit.SECONDS);//阻塞，
                    printReadWrite(reentrantLock);
                } catch (Exception e) {
                    Assert.fail(e.getMessage());
                } finally {
                    writeLock.unlock();//抛出IllegalMonitorStateException异常，没释放读锁，会造成死锁
                }
                Assert.fail();
            } catch (Exception e) {
                Assert.assertEquals(e.getClass() , IllegalMonitorStateException.class);
            } finally {
                readLock.unlock();
            }
        }).start();

        try {
            Thread.sleep(113000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite2ReadLock() {
        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
        new Thread(() -> {
            ReentrantReadWriteLock.WriteLock writeLock = reentrantLock.writeLock();
            ReentrantReadWriteLock.ReadLock readLock = reentrantLock.readLock();
            try {
                writeLock.lock();
                try {
                    readLock.lock();//只能当前线程获取读锁？
                    printReadWrite(reentrantLock);
                } catch (Exception e) {
                    Assert.fail(e.getMessage());
                } finally {
                    readLock.unlock();
                }

            } catch (Exception e) {
                Assert.fail(e.getMessage());
            } finally {
                writeLock.unlock();
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printReadWrite(ReentrantReadWriteLock reentrantLock) {
        try {
            synchronized (reentrantLock) {
                Lock rLock = reentrantLock.readLock();
                Lock wLock = reentrantLock.writeLock();
                Field lock = ReentrantReadWriteLock.class.getDeclaredField("sync");
                Field rlockF = rLock.getClass().getDeclaredField("sync");
                Field wLockF = wLock.getClass().getDeclaredField("sync");
                lock.setAccessible(true);
                rlockF.setAccessible(true);
                wLockF.setAccessible(true);
                Object mlockObj = lock.get(reentrantLock);
                Object rlockObj = rlockF.get(rLock);
                Object wlockObj = wLockF.get(wLock);

                Assert.assertEquals(mlockObj, rlockObj);
                Assert.assertEquals(rlockObj, wlockObj);
                //syn
                printAqs(mlockObj, "main");
                System.out.println();
            }
        } catch (Exception e) {
        }
    }

    public static void printAqs(Object lock, String label) {
        if (lock == null)
            return;
//        System.out.print(lock.toString().substring(lock.getClass().getName().length())+" ");
        try {

            Field state = AbstractQueuedSynchronizer.class.getDeclaredField("state");
            Field head = AbstractQueuedSynchronizer.class.getDeclaredField("head");
            Field tail = AbstractQueuedSynchronizer.class.getDeclaredField("tail");
            Field exclusiveOwnerThread = AbstractOwnableSynchronizer.class.getDeclaredField("exclusiveOwnerThread");


            Field cachedHoldCounter = lock.getClass().getSuperclass().getDeclaredField("cachedHoldCounter");
            Field firstReader = lock.getClass().getSuperclass().getDeclaredField("firstReader");
            Field firstReaderHoldCount = lock.getClass().getSuperclass().getDeclaredField("firstReaderHoldCount");
            firstReaderHoldCount.setAccessible(true);
            cachedHoldCounter.setAccessible(true);
            firstReader.setAccessible(true);
            Object cachedHoldCaounterObj = cachedHoldCounter.get(lock);
            Object cachedHoldCaounterCount = "";
            if (cachedHoldCaounterObj != null) {
                Field count = cachedHoldCaounterObj.getClass().getDeclaredField("count");
                count.setAccessible(true);
                cachedHoldCaounterCount = count.get(cachedHoldCaounterObj) == null ? "" : count.get(cachedHoldCaounterObj);
            }


            synchronized (lock) {
                state.setAccessible(true);
                int stateObj = (int) state.get(lock);
                System.out.print("Sync@" + label + " state(" + Integer.toBinaryString(stateObj) + ")  ");
                exclusiveOwnerThread.setAccessible(true);
                Object exclusiveOwnerThreadObj = exclusiveOwnerThread.get(lock) == null ? "null" : ((Thread) exclusiveOwnerThread.get(lock)).getName();
                System.out.print(" OwnerThread " + exclusiveOwnerThreadObj + "  ");
                System.out.print(" firstReaderHoldCount " + firstReaderHoldCount.get(lock) + "  ");
                System.out.print(" cachedHoldCaounterCount " + cachedHoldCaounterCount + "  ");
                System.out.print(" firstReader " + (firstReader.get(lock)==null?"null ":((Thread)firstReader.get(lock)).getName()) + "  ");


                tail.setAccessible(true);
                Object tailObj = tail.get(lock);
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

    public static void printShareObj(){
        Class<?> aClass = null;
        try {
            aClass = Class.forName("java.util.concurrent.locks.AbstractQueuedSynchronizer$Node");
            Field shared = aClass.getDeclaredField("SHARED");
            shared.setAccessible(true);
            Object o = shared.get(aClass);
            System.out.println("SHARED " + o.toString().substring(o.getClass().getName().length()));
        } catch ( Exception e) {
            e.printStackTrace();
        }

    }
}
