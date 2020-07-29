package edu.ptu.javatest._20_ooad._21_create;

import org.junit.Test;

public class CloneTest {
    public static class cloneTarget{
        int a;
        Integer aRef;
    }
    @Test
    public void testClone(){
        cloneTarget cloneTarget = new cloneTarget();
        cloneTarget.a=1;
        cloneTarget.aRef=2;
    }
}
