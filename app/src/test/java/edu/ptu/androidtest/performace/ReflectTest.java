package edu.ptu.androidtest.performace;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import edu.ptu.androidtest.bean.Cat;
import edu.ptu.androidtest.bean.Dog;

/**
 * Created by anshu.wang on 2016/3/25.
 */
public class ReflectTest {
    //    @Test
    public void testReflect() {
        try {
            long last = System.nanoTime();
            for (int i = 0; i < 3_000; i++) {
                createReflectDog();
            }
            System.out.println(System.nanoTime() - last);

            last = System.nanoTime();
            for (int i = 0; i < 3_000; i++) {
                createCat();
//                createDog();
//                createReflectDog();
            }
            System.out.println(System.nanoTime() - last);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * 测出反射时间 /1000
     */
    public void testReflectDog() {
        try {
            long last = System.nanoTime();
            for (int i = 0; i < 1_000; i++) {
                Method d = Dog.class.getMethod("toString");
                d.invoke(new Dog());
            }
            System.out.println(System.nanoTime() - last);

            Method d = Dog.class.getMethod("toString");
            last = System.nanoTime();
            for (int i = 0; i < 1_000; i++) {
                d.invoke(new Dog());
            }
            System.out.println(System.nanoTime() - last);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @Test
    public void testCreateDogDog() {
        try {
            long last = System.nanoTime();
            for (int i = 0; i < 1_000; i++) {
//                createReflectDog();
                createDog();
//                createCat();
            }
            System.out.println(System.nanoTime() - last);

            last = System.nanoTime();
            for (int i = 0; i < 1_000; i++) {
                createDog();
//                createReflectDog();
            }
            System.out.println(System.nanoTime() - last);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreatCatDog() {
        try {
            long last = System.nanoTime();
            for (int i = 0; i < 1_000; i++) {
//                createReflectDog();
//                createDog();
                createCat();
            }
            System.out.println(System.nanoTime() - last);

            last = System.nanoTime();
            for (int i = 0; i < 1_000; i++) {
                createDog();
//                createReflectDog();
            }
            System.out.println(System.nanoTime() - last);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Object> list = new ArrayList<>(6_000);

    public void createDog() {
        Dog dog = new Dog();
        list.add(dog);
    }

    public void createCat() {
        Cat cat = new Cat();
        list.add(cat);
    }

    public void createReflectDog() throws Exception {
//        Class<?> aClass = Class.forName("edu.ptu.test.bean.Dog");
        Object o = Dog.class.newInstance();

    }
}
