package edu.ptu.javatest._80_storage._70_cache._40_generics;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.text.AsyncBoxView;

public class Array  {
    public static class Child extends Parent{

    }public static class Parent{

    }

    public static class G<T>{

    }
    @Test
    public void testArr(){
//         new Child[2]; Assert.assertTrue( );
        Child[] children = new Child[2];
        Assert.assertTrue(children instanceof Parent[]);
        G<Child> childG = new G<>();
        Assert.assertTrue(childG instanceof G);
    }
}
