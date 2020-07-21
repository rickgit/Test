package edu.ptu.javatest._60_dsa;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedList;

public class LinkedListTest {
    @Test
    public void testLinked(){
        LinkedList<Object> list = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        try {
            Field first = list.getClass().getDeclaredField("first");
            Field last = list.getClass().getDeclaredField("last");

            last.setAccessible(true);
            Object tailObj = last.get(list);
            printNode(tailObj,"last",false);

            first.setAccessible(true);
            Object headObj = first.get(list);
            printNode(headObj,"first",true);

            Object objIter=tailObj;
            while (objIter != null) {
                printNode(objIter," prev->",false);

//                    Field next = objIter.getClass().getDeclaredField("next");//
                Field prev = objIter.getClass().getDeclaredField("prev");//

                prev.setAccessible(true);
                objIter = prev.get(objIter);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void printNode(Object nodeObj,String flag,boolean endNewLine)   {
        if (nodeObj == null)
            return;
        try {
            Field next = nodeObj.getClass().getDeclaredField("next");
            Field prev = nodeObj.getClass().getDeclaredField("prev");
            Field item = nodeObj.getClass().getDeclaredField("item");



            System.out.print(" "+flag+" Node" + nodeObj.toString().substring(nodeObj.getClass().getName().length()) + "");

            item.setAccessible(true);
            Object o = item.get(nodeObj);

            System.out.print("("+ o+ " )");

            if (endNewLine)
                System.out.println( );
        } catch ( Exception e) {
            e.printStackTrace();
        }



    }
}
