package edu.ptu.javatest._90_jcu._11_container;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//读数量大于更新，优于arrayList
//每次添加 都会复制，效率低；适合迭代
public class _01_CopyOnWriteArrayListTest {
    @Test
    public void testThreadIterAndAdd(){
        ThreadIterAndAdd threadIterAndAdd = new ThreadIterAndAdd();
        for (int i = 0; i < 10; i++) {
            new Thread(threadIterAndAdd).start();
        }

    }
    public static class ThreadIterAndAdd implements Runnable{
        List<Object> list= Collections.synchronizedList(new ArrayList<Object>());
        public ThreadIterAndAdd(){
            list.add("sd");
            list.add("dfs");
            list.add("d");
            list.add("asd");
        }
        @Override
        public void run() {
            Iterator<Object> iterator = list.iterator();
            try {
                while (iterator.hasNext()) {
                    iterator.next();
                    list.add("ad");
                }
            }catch (Exception e){
                Assert.assertEquals(e.getClass(), ConcurrentModificationException.class);
                return;
            }
            Assert.fail();
        }
    }

    @Test
    public void testThreadIterAndCopyWrite(){
        ThreadIterAndCopyWrite threadIterAndAdd = new ThreadIterAndCopyWrite();
        for (int i = 0; i < 10; i++) {
            new Thread(threadIterAndAdd).start();
        }

    }
    public static class ThreadIterAndCopyWrite implements Runnable{
        List<Object> list= new CopyOnWriteArrayList<>();
        public ThreadIterAndCopyWrite(){
            list.add("sd");
            list.add("dfs");
            list.add("d");
            list.add("asd");
        }
        @Override
        public void run() {
            Iterator<Object> iterator = list.iterator();
            try {
                while (iterator.hasNext()) {
                    iterator.next();
                    list.add("ad");
                }
            }catch (Exception e){
                Assert.fail();
            }
            System.out.println(list.size());

        }
    }
}
