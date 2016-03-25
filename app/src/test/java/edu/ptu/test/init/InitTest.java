package edu.ptu.test.init;

import edu.ptu.test.bean.Dog;
import edu.ptu.test.bean.HasiqiDog;

/**
 * Created by anshu.wang on 2016/3/25.
 */
public class InitTest {
//    @Test
    public void testInit() {
        long last = System.nanoTime();
        Object o = new Object();
        System.out.println(System.nanoTime() - last);

        last = System.nanoTime();
        Object o1 = new Object();
        System.out.println(System.nanoTime() - last);

        last = System.nanoTime();
        new Object();
        System.out.println(System.nanoTime() - last);
    }

    public static void main(String[] args) {
        System.out.println("");
    }
//    @Test
    public void testDogObj() {
        long last = System.nanoTime();
        Object o = new Object();
        System.out.println(System.nanoTime() - last);

        last = System.nanoTime();
        o = new Dog();
        System.out.println(System.nanoTime() - last);

        last = System.nanoTime();
        o = new HasiqiDog();
        System.out.println(System.nanoTime() - last);

        last = System.nanoTime();
        o = new HasiqiDog();
        System.out.println(System.nanoTime() - last);
    }

}
