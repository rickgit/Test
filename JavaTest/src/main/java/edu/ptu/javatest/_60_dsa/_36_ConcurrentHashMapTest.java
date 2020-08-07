package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class _36_ConcurrentHashMapTest {
    @Test
    public void testConcurentHashMap() {
        ConcurrentHashMap<Object, Object> chm = new ConcurrentHashMap<>();
        chm.put("", "");
        chm.putAll(new HashMap<>());

    }

    @Test
    public void testLeadingZeros() {
        int testInt = 1;
        System.out.println(Integer.toBinaryString(testInt));
        int result = Integer.numberOfLeadingZeros(testInt);//返回二进制高位，0的个数
        Assert.assertEquals(result, 32 - Integer.toBinaryString(testInt).length());
    }

    @Test
    public void testresizeStamp() {
        System.out.println(Integer.toBinaryString(Integer.numberOfLeadingZeros(23)));
        System.out.println(resizeStamp(23));
        System.out.println(Integer.toBinaryString(resizeStamp(23)));
    }

    @Test
    public void testTableSizeFor(){

    }

    static final int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }

    @Test
    public void testGrow() {
        ConcurrentHashMap<Object, Object> chm = new ConcurrentHashMap<>();
        for (int i = 0; i < 9; i++) {
            chm.put(new _32_HashMapTest.HashObj(i), "");
        }
        printTree(chm);
    }

    @Test
    @SuppressWarnings("all")
    public void testThreadLocalRandom() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Method getProbe = ThreadLocalRandom.class.getDeclaredMethod("getProbe");//每个线程获取的数值不一样
                        getProbe.setAccessible(true);


                        Object invoke = getProbe.invoke(ThreadLocalRandom.class);
                        if ((int) invoke == 0) {//  force initialization
                            Method localInit = ThreadLocalRandom.class.getDeclaredMethod("localInit");
                            localInit.setAccessible(true);

                            localInit.invoke(ThreadLocalRandom.class);
                            invoke = getProbe.invoke(ThreadLocalRandom.class);
                        }
                        System.out.println(Thread.currentThread()+"getProbe " + Integer.toBinaryString((Integer) invoke));
                        invoke = getProbe.invoke(ThreadLocalRandom.class);
                        System.out.println(Thread.currentThread()+"getProbe " + Integer.toBinaryString((Integer) invoke));

                        Method advanceProbe = ThreadLocalRandom.class.getDeclaredMethod("advanceProbe",int.class);//根据旧probe值生成新的probe值
                        advanceProbe.setAccessible(true);
                        invoke = advanceProbe.invoke(ThreadLocalRandom.class,invoke);//根据旧probe值生成新的probe值。
                        System.out.println(Thread.currentThread()+"advanceProbe " + Integer.toBinaryString((Integer) invoke));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testThread() {
        ConcurrentHashMap<Object, Object> chashmap = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        StringBuffer stringBuffer = new StringBuffer("0");
        for (int i = 0; i < 129; i++) {
            stringBuffer.replace(0,1,i+"");
            executorService.execute(() -> {
                Thread.yield();
                chashmap.put(new _32_HashMapTest.HashObj(Integer.parseInt(stringBuffer.substring(0,1).toLowerCase())), new Random(23).nextInt(23));
//                printTree(chashmap);
            });
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 树化需要两个条件，总容量及hash冲突值
     */
    public void printTree(ConcurrentHashMap map) {
        try {

            Field table = ConcurrentHashMap.class.getDeclaredField("table");
            Field nextTable = ConcurrentHashMap.class.getDeclaredField("nextTable");
            Field baseCount = ConcurrentHashMap.class.getDeclaredField("baseCount");
            Field counterCells = ConcurrentHashMap.class.getDeclaredField("counterCells");
            Field sizeCtl = ConcurrentHashMap.class.getDeclaredField("sizeCtl");
            Field transferIndex = ConcurrentHashMap.class.getDeclaredField("transferIndex");
            Field cellsBusy = ConcurrentHashMap.class.getDeclaredField("cellsBusy");

            synchronized (map) {
                table.setAccessible(true);
                Object[] tableObj = (Object[]) table.get(map);
                if (tableObj != null)
                    System.out.print(" tableObj " + tableObj.length + "  ");

                nextTable.setAccessible(true);
                Object[] nextTabObj = (Object[]) nextTable.get(map);
                System.out.print(" nextTabObj " + (nextTabObj == null ? "null" : nextTabObj.length) + "  ");

                baseCount.setAccessible(true);
                Long baseCountObj = (Long) baseCount.get(map);
                System.out.print(" baseCount " + baseCountObj + "  ");

                counterCells.setAccessible(true);
                Object[] counterCellsObj = (Object[]) counterCells.get(map);
                System.out.print(" counterCellsObj " + counterCellsObj + "  ");

                sizeCtl.setAccessible(true);
                int sizeCtlObj = (int) sizeCtl.get(map);
                System.out.print(" sizeCtl " +sizeCtlObj+":"+ Integer.toBinaryString(sizeCtlObj) + "  ");

                transferIndex.setAccessible(true);
                int transferIndexObj = (int) transferIndex.get(map);
                System.out.print(" transferIndex " + transferIndexObj + "  ");

                cellsBusy.setAccessible(true);
                int cellsBusyObj = (int) cellsBusy.get(map);
                System.out.print(" cellsBusy " + cellsBusyObj + "  ");
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }


    }
}
