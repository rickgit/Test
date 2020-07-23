package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ptu.javatest._80_storage._80_file.classfile.RefectTest;

import static edu.ptu.javatest._80_storage._80_file.classfile.RefectTest.getRefFieldBool;
import static edu.ptu.javatest._80_storage._80_file.classfile.RefectTest.getRefFieldObj;


public class HashMapTest {
    @Test
    public void testArrayAddNull() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, null);
        Assert.assertEquals(map.size(), 1);
    }

    @Test
    public void testArrayRemove() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put("" + i, "" + i);
        }
        for (int i = 0; i < 1000; i++) {
            map.remove("" + i);
        }
        System.out.println(map.size());
        System.out.println(getArrayAlloc(map));

    }

    @Test
    public void testArrayAddSame() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, null);
        map.put(null, null);
        Assert.assertEquals(map.size(), 1);
    }

    @Test
    @SuppressWarnings("all")
    public void testPutIfAbsent() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        System.out.println(map.get("1"));
        map.put("1", "2");
        System.out.println(map.get("1"));
        map.putIfAbsent("1", "3");
        System.out.println(map.get("1"));
        map.put("1", null);
        System.out.println(map.get("1"));
        map.putIfAbsent("1", "3");
        System.out.println(map.get("1"));
        Assert.assertEquals(map.size(), 1);
    }

    @Test
    public void testTableSizeForMethod() {
        //Returns a power of two size for the given target capacity.
        try {
            Method tableSizeFor = HashMap.class.getDeclaredMethod("tableSizeFor", int.class);
            tableSizeFor.setAccessible(true);
            Object invoke = tableSizeFor.invoke(HashMap.class, 9);
            System.out.println(invoke);
        } catch (Exception e) {//NoSuchMethodException | IllegalAccessException | InvocationTargetException e
            e.printStackTrace();
        }

    }

    @Test
    public void testInitSize() {
        HashMap<Object, String> map = new HashMap<>(1, 1);//initialCapacity,loadFactor
        Assert.assertEquals(-1, getArrayAlloc(map));
        map.put(null, null);
        Assert.assertEquals(1, getArrayAlloc(map));

        testTableSizeForMethod();
        map = new HashMap<>(2, 1);
        Assert.assertEquals(-1, getArrayAlloc(map));
        map.put(null, null);
        Assert.assertEquals(2, getArrayAlloc(map));


        map = new HashMap<>(3, 1);//容量自动调整是2的整数倍
        Assert.assertEquals(-1, getArrayAlloc(map));
        map.put(null, null);
        Assert.assertEquals(4, getArrayAlloc(map));


    }

    @Test
    public void testResizeNoTree() {
        HashMap map = new HashMap<>(1, 1);
        for (int i = 0; i < 9; i++) {//达到冲突树化阀值（不包括哈希表的节点），Map没有达到总量阀值，扩容
            map.put(new HashObj(1), i + "");//填入hash 相同的值
        }
        Assert.assertEquals(map.size(), 9);
        Assert.assertEquals(getArrayAlloc(map), 16);
        for (int i = 9; i < 17; i++) {//达到冲突树化阀值，Map没有达到总量阀值，扩容
            map.put(new HashObj(2), i + "");//填入hash 相同的值
        }
        Assert.assertEquals(map.size(), 17);
        Assert.assertEquals(getArrayAlloc(map), 32);
//        printArrayNode(map, 1);
    }

    @Test
    //TODO 未完成
    public void testDec2NoTree() {
        try {
            Field treeCap = HashMap.class.getDeclaredField("MIN_TREEIFY_CAPACITY");
            treeCap.setAccessible(true);
            Field threshold = HashMap.class.getDeclaredField("TREEIFY_THRESHOLD");
            threshold.setAccessible(true);
            Integer cap = (Integer) treeCap.get(HashMap.class);
            HashMap<Object, Object> map = new HashMap<Object, Object>(cap, 1f);
            Integer integer = (Integer) threshold.get(HashMap.class) + 1;
            ArrayList<Object> list = new ArrayList<>();
            for (int i = 0; i < integer; i++) {
                HashObj k = new HashObj(1);
                map.put(k, "" + i);
                list.add(k);
            }
            Assert.assertEquals(map.size(), integer.intValue());
            Field elementData = null;
            elementData = map.getClass().getDeclaredField("table");
            elementData.setAccessible(true);
            Assert.assertEquals((((Object[]) (elementData.get(map)))[1]).getClass().toString(), "class java.util.HashMap$TreeNode");

            System.out.println("树高 " + getTreeDepth((((Object[]) (elementData.get(map)))[1])));
            //Minitree
            Integer unTreeifytry = 6;//拆分表时，节点小于就链表化
            //
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 树化需要两个条件，总容量及hash冲突值
     */
    @Test
    public void testTree() {
        try {
            Field treeCap = HashMap.class.getDeclaredField("MIN_TREEIFY_CAPACITY");
            treeCap.setAccessible(true);
            Field threshold = HashMap.class.getDeclaredField("TREEIFY_THRESHOLD");
            threshold.setAccessible(true);
            Integer cap = (Integer) treeCap.get(HashMap.class);
            HashMap<Object, Object> map = new HashMap<Object, Object>(cap, 1f);
            Integer integer = (Integer) threshold.get(HashMap.class) + 1;
            for (int i = 0; i < integer; i++) {
                map.put(new HashObj(1), "" + i);
            }
            Assert.assertEquals(map.size(), integer.intValue());
            Field elementData = null;
            elementData = map.getClass().getDeclaredField("table");
            elementData.setAccessible(true);
            Assert.assertEquals((((Object[]) (elementData.get(map)))[1]).getClass().toString(), "class java.util.HashMap$TreeNode");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public static class HashObj {
        int hash;

        HashObj(int hash) {
            this.hash = hash;
        }

        @Override
        public int hashCode() {
            return hash;
        }
    }


    private static int getArrayAlloc(Object map) {
        try {
            Field elementData = null;
            elementData = map.getClass().getDeclaredField("table");
            elementData.setAccessible(true);
            Object[] objects = (Object[]) elementData.get(map);
            return objects.length;
        } catch (Exception e) {//throws NoSuchFieldException, IllegalAccessException
            return -1;
        }
    }


    public static int getTreeDepth(Object rootNode) {
        if (rootNode == null || !rootNode.getClass().toString().equals("class java.util.HashMap$TreeNode"))
            return 0;

        return rootNode == null ? 0 : (1 + Math.max(getTreeDepth(getRefFieldObj(rootNode, rootNode.getClass(), "left")), getTreeDepth(getRefFieldObj(rootNode, rootNode.getClass(), "right"))));
    }

    @Test
    public void testPrintTree() {
        HashMap hashMapTest = new HashMap(64);
        for (int i = 0; i < 9; i++) {
            hashMapTest.put(new HashObj(1), i);
        }
        Object[] table = (Object[]) getRefFieldObj(hashMapTest, hashMapTest.getClass(), "table");
        printTreeNode(table[1]);
        System.out.println();
    }

    private static void printTreeNode(Object rootNode) {//转化为堆
        if (rootNode == null || !rootNode.getClass().toString().equals("class java.util.HashMap$TreeNode"))
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
            int index = (i - 1) / 2;
            if (objects[index] != null) {
                if (i % 2 == 0)
                    objects[i] = getRefFieldObj(objects[index], objects[index].getClass(), "left");
                else
                    objects[i] = getRefFieldObj(objects[index], objects[index].getClass(), "right");
            }
        }
        StringBuilder sb = new StringBuilder();
        String space = "       ";
        for (int i = 0; i < treeDepth + 1; i++) {
            sb.append(space);
        }
        int nextlineIndex = 0;
        for (int i = 0; i < objects.length; i++) {//new line: 0,1 ,3,7
            //print space
            //print value
            if (nextlineIndex == i) {
                System.out.println();
                System.out.println();
                if (sb.length() >= space.length()) {
                    sb.delete(0, space.length());
                }

                nextlineIndex = i * 2 + 1;
            }
            System.out.print(sb.toString());

            if (objects[i]!=null) {
                Object value = getRefFieldObj(objects[i], objects[i].getClass().getSuperclass().getSuperclass(), "value");
                boolean red = getRefFieldBool(objects[i], objects[i].getClass(), "red");
                System.out.print(""+value+"("+(red?"r":"b")+")");
            } else
                System.out.print("nil");

        }

    }


    private static void printArrayNode(Object map, int tabindex) {
        try {
            Field elementData = null;
            elementData = map.getClass().getDeclaredField("table");
            elementData.setAccessible(true);
            Object[] objects = (Object[]) elementData.get(map);
            if (objects.length > tabindex) {
                Object rootNode = objects[tabindex];
                while (rootNode != null) {
                    if (rootNode.getClass().toString().equals("class java.util.HashMap$TreeNode")) {
//                        Field value = rootNode.getClass().getSuperclass().getSuperclass().getDeclaredField("value");
//                        value.setAccessible(true);
//                        System.out.print("(Node " + value.get(rootNode) + " )");
//
//                        Field prev = rootNode.getClass().getDeclaredField("prev");
//                        prev.setAccessible(true);
//                        Object prevNode = prev.get(rootNode);
//                        System.out.print("(prev " + prevNode + " )");
//
//                        Field parent = rootNode.getClass().getDeclaredField("parent");
//                        parent.setAccessible(true);
//                        Object parentNode = parent.get(rootNode);
//                        System.out.print("(prev " + prevNode + " )");
//
                        rootNode = null;
                    }

                    if (rootNode.getClass().toString().equals("class java.util.HashMap$Node")) {
                        Field value = rootNode.getClass().getDeclaredField("value");
                        value.setAccessible(true);
                        Object valueOjb = value.get(rootNode);
                        System.out.print("(Node " + valueOjb + " )");
                        System.out.print("->");

                        Field next = rootNode.getClass().getDeclaredField("next");
                        next.setAccessible(true);
                        Object nextOjb = next.get(rootNode);
                        rootNode = nextOjb;

                    }
                }
            }

        } catch (Exception e) {//throws NoSuchFieldException, IllegalAccessException
        }
    }
}
