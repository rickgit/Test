package edu.ptu.javatest._60_dsa;

import org.junit.Test;

import java.util.TreeMap;

import static edu.ptu.javatest._20_ooad._50_dynamic._00_ReflectionTest.getRefFieldBool;
import static edu.ptu.javatest._20_ooad._50_dynamic._00_ReflectionTest.getRefFieldObj;

public class _35_TreeMapTest {
    @Test
    public void testPrintTreeMap() {
        TreeMap hashMapTest = new TreeMap<>();
        for (int i = 0; i < 6; i++) {
            hashMapTest.put(new TMHashObj(1,i*2 ), i*2  );
        }
        Object  table =   getRefFieldObj(hashMapTest, hashMapTest.getClass(), "root");
        printTreeMapNode(table);
        hashMapTest.put(new TMHashObj(1,9), 9);
        printTreeMapNode(table);
        System.out.println();
    }
    public static int getTreeDepth(Object rootNode) {
        if (rootNode == null || !rootNode.getClass().toString().equals("class java.util.TreeMap$Entry"))
            return 0;

        return rootNode == null ? 0 : (1 + Math.max(getTreeDepth(getRefFieldObj(rootNode, rootNode.getClass(), "left")), getTreeDepth(getRefFieldObj(rootNode, rootNode.getClass(), "right"))));
    }
    public static void printTreeMapNode(Object rootNode) {//转化为堆
        if (rootNode == null || !rootNode.getClass().toString().equals("class java.util.TreeMap$Entry"))
            return;
        int treeDepth = getTreeDepth(rootNode);
        Object[] objects = new Object[(int) (Math.pow(2, treeDepth) - 1)];
        objects[0] = rootNode;
        //         objects[0]=rootNode;
//         objects[1]=getRefFieldObj(objects,objects.getClass(),"left");
//         objects[2]=getRefFieldObj(objects,objects.getClass(),"right");
//
//        objects[3]=getRefFieldObj(objects[1],objects[1].getClass(),"left");
//        objects[4]=getRefFieldObj(objects[1],objects[1].getClass(),"right");
//        objects[5]=getRefFieldObj(objects[2],objects[3].getClass(),"left");
//        objects[6]=getRefFieldObj(objects[2],objects[4].getClass(),"right");

        for (int i = 1; i < objects.length; i++) {//数组打印
            int index = (i - 1) / 2;//parent
            if (objects[index] != null) {
                if (i % 2 == 1)
                    objects[i] = getRefFieldObj(objects[index], objects[index].getClass(), "left");
                else
                    objects[i] = getRefFieldObj(objects[index], objects[index].getClass(), "right");
            }
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder outSb = new StringBuilder();
        String space = "       ";
        for (int i = 0; i < treeDepth + 1; i++) {
            sb.append(space);
        }
        int nextlineIndex = 0;
        for (int i = 0; i < objects.length; i++) {//new line: 0,1 ,3,7
            //print space
            //print value
            if (nextlineIndex == i) {
                outSb.append("\n\n");
                if (sb.length() >= space.length()) {
                    sb.delete(0, space.length());
                }

                nextlineIndex = i * 2 + 1;
            }
            outSb.append(sb.toString());
            if (objects[i] != null) {
                Object value = getRefFieldObj(objects[i], objects[i].getClass(), "value");
                boolean red = !getRefFieldBool(objects[i], objects[i].getClass(), "color");// BLACK = true;
                String result = "" + value + "(" + (red ? "r" : "b") + ")";
                outSb.append(result);
            } else {
                outSb.append("nil");
            }

        }
        System.out.println(outSb.toString());
    }

    public static class TMHashObj implements Comparable{
        int hash;
        int value;

        TMHashObj(int hash, int value) {
            this.hash = hash;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof TMHashObj){
                return this.value-((TMHashObj) o).value;
            }
            return value-o.hashCode();
        }
    }


}
