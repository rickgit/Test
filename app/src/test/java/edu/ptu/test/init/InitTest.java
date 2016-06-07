package edu.ptu.test.init;


import org.junit.Test;

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
    @Test
    public void testCreateAutoBoxing(){
        Long Ii=0l;
        long last = System.nanoTime();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Ii+=i;
        }
        System.out.println(System.nanoTime() - last);
        long longSimple=0;
        last = System.nanoTime();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            longSimple+=i;
        }
        System.out.println(System.nanoTime() - last);
    }

    /**
     * 增加终结方法的效率
     *
     * effictive java ,第二章，第七条
     */
    public void testAddFinalizer(){

    }
    /**
     * 增加到set的两个对象，这两个对象根据一个属性判断是否相等，如果在加入后，两个对象的属性值设置相等后，两个对象是否还会存在set中。
     *
     * effictive java ,第三章，第八条，一致性
     */
    public void testEquelConsistency(){

    }




}
