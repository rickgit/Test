package edu.ptu.androidtest._20_dsa;

import org.junit.Test;

import java.lang.reflect.Field;

public class StringTest {
    @Test
    public void test(){
        try {
            Field declaredField = String.class.getDeclaredField("count");
            declaredField.setAccessible(true);
            Object sgring = declaredField.get("231");
            Object as2 =declaredField.get("王");
            System.out.println();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}
