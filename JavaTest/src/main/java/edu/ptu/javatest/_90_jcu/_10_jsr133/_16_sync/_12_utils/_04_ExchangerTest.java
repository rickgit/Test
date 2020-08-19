package edu.ptu.javatest._90_jcu._10_jsr133._16_sync._12_utils;

import org.junit.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import edu.ptu.javatest._20_ooad._50_dynamic._00_ReflectionTest;

//与SynchronousQueue区别
public class _04_ExchangerTest {
    @Test
    public void test() {
        Exchanger<String> exchanger = new Exchanger<>();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
//                        printExchangeNode(exchanger);
                        Thread.sleep(300);
                        String ex1 = exchanger.exchange("线程" + finalI);
                        printExchangeNode(exchanger);
//                        System.out.println("线程" + finalI + " 交换后的结果：" + ex1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "线程" + i).start();
        }


        printExchangeNode(exchanger);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printExchangeNode(Object exchange) {
        //无竞争，单槽交换
        Object slot = _00_ReflectionTest.getRefFieldObj(exchange, exchange.getClass(), "slot");
        if (slot != null)
            slot = _00_ReflectionTest.getRefFieldObj(slot, slot.getClass(), "item");
        Object bound = _00_ReflectionTest.getRefFieldObj(exchange, exchange.getClass(), "bound");
        //存在竞争的，使用多槽交换
        Object[] arena = (Object[]) _00_ReflectionTest.getRefFieldObj(exchange, exchange.getClass(), "arena");
        StringBuilder sb = new StringBuilder();
        sb.append("arena:[");
        if (arena != null)
            for (int i = 0; i < arena.length; i++) {
                Object object = arena[i];
                if (object != null) {
                    sb.append("{item" + i + ":" + _00_ReflectionTest.getRefFieldObj(object, object.getClass(), "item"));
                    sb.append(" index :" + _00_ReflectionTest.getRefFieldObj(object, object.getClass(), "index"));
                    sb.append(" bound :" + _00_ReflectionTest.getRefFieldObj(object, object.getClass(), "bound"));
                    sb.append(" collides :" + _00_ReflectionTest.getRefFieldObj(object, object.getClass(), "collides"));
                    sb.append("},");
                }


            }
        sb.append("]");
        System.out.println("slot :" + slot + " bound :" + bound + " " + sb);

    }


}
