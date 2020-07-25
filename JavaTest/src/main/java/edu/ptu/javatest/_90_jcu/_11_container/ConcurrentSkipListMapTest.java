package edu.ptu.javatest._90_jcu._11_container;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

import edu.ptu.javatest._20_ooad._50_dynamic._00_ReflectionTest;

//优于TreeMap
public class ConcurrentSkipListMapTest {
    @Test
    public void testSkipList(){
        ConcurrentSkipListMap<Object, Object> skipListMap = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 10; i++) {
            skipListMap.put(new Random().nextInt(23),i);
        }

        printSkipList(skipListMap);
        System.out.println(skipListMap);
    }

    public void printSkipList(Object obj){
        Object indexObj = _00_ReflectionTest.getRefFieldObj(obj, obj.getClass(), "head");//headindex
        Object descendingMapObj = _00_ReflectionTest.getRefFieldObj(obj, obj.getClass(), "descendingMap");
//        Object levelInt = RefectTest.getRefFieldInt(obj, obj.getClass(), "level");
//        Object adderObj = RefectTest.getRefFieldObj(obj, obj.getClass(), "adder");
        while (indexObj!=null){
            Object level = _00_ReflectionTest.getRefFieldObj(indexObj, indexObj.getClass(), "level");
            Object node = _00_ReflectionTest.getRefFieldObj(indexObj, indexObj.getClass().getSuperclass(), "node");
            System.out.print("  Node "+node.toString().substring(node.getClass().getName().length()));
            while (node!=null){
                Object value = _00_ReflectionTest.getRefFieldObj(node, node.getClass(), "value");
                Object key = _00_ReflectionTest.getRefFieldObj(node, node.getClass(), "key");
                System.out.print(" {"+key+":"+(value instanceof Integer?value:"obj")+"} ->");
                node= _00_ReflectionTest.getRefFieldObj(node, node.getClass(), "next");
            }
            //class java.util.concurrent.ConcurrentSkipListMap$Index
            Object rightIndex = _00_ReflectionTest.getRefFieldObj(indexObj, indexObj.getClass().getSuperclass(), "right");
//            while (rightIndex!=null){
//
//            }
            //class java.util.concurrent.ConcurrentSkipListMap$HeadIndex
            Object downIndex = _00_ReflectionTest.getRefFieldObj(indexObj, indexObj.getClass().getSuperclass(), "down");
            indexObj=downIndex;
            System.out.println();
        }
        System.out.println();

    }
}
