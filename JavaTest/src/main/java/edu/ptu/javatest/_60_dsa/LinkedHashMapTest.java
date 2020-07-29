package edu.ptu.javatest._60_dsa;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import edu.ptu.javatest._20_ooad._50_dynamic._00_ReflectionTest;

public class LinkedHashMapTest {
    @Test
    public void testLinkedHashMap(){
        LinkedHashMap<Object, Object> lhm = new LinkedHashMap<Object, Object>(16,0.75f,true);
        for (int i = 0; i < 5; i++) {
            lhm.put(i,i);
        }
        printLinkHashMapList(lhm);
        lhm.get(3);
        printLinkHashMapList(lhm);
    }

    private void printLinkHashMapList(Object hm) {
        try {

            Object headNode = _00_ReflectionTest.getRefFieldObj(hm, hm.getClass(), "head");
            while(headNode!=null){
                System.out.println( _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass().getSuperclass(), "key"));
                Object before = _00_ReflectionTest.getRefFieldObj(headNode, headNode.getClass(), "after");

                headNode=before;
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
