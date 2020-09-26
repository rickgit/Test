package edu.ptu.androidtest._20_dsa;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Random;

import edu.ptu.javatest._60_dsa._22_StringTest;

public class StringTest {
    @Test
    public void test(){
        try {
            Field declaredField = String.class.getDeclaredField("count");
            declaredField.setAccessible(true);
            Object sgring = declaredField.get("231");
            Object as2 =declaredField.get("王");
            System.out.println();

            System.out.println(_22_StringTest.getArrayAlloc("11"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
@Test
    public void testAlth() {
        int i=0;
        int x = i++ + 3<<2;
        System.out.println("结果 "+x);
    }
}
