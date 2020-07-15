package edu.ptu.androidtest._20_dsa;


import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class ArrayMapTest {
    @Test
    public void testInit() {
        ArrayMap<Object, Object> arrayM = new ArrayMap<>();//cap 0,idhash false
        Assert.assertEquals(getArrayAlloc(arrayM), 0);
        for (int i = 0; i < 16; i++) {
            arrayM = new ArrayMap<>(i);
            Assert.assertEquals(getArrayAlloc(arrayM), i);
        }
    }

    @Test
    public void testGrow() {
        ArrayMap<Object, Object> arrayM = new ArrayMap<>();//cap 0,idhash false
        Assert.assertEquals(getArrayAlloc(arrayM), 0);
        for (int i = 0; i < 28; i++) {
            arrayM.put(i, i);//grow 4 8 (osize+(osize>>1))
        }
        Assert.assertEquals(getArrayAlloc(arrayM), 40);//4 8 12 18 27 40
    }

    @Test
    public void testShrink() {
        ArrayMap<Object, Object> arrayM = new ArrayMap<>();//cap 0,idhash false
        Assert.assertEquals(getArrayAlloc(arrayM), 0);
        for (int i = 0; i < 27; i++) {//put: grow from
            arrayM.put(i, i);//grow 4 8 (osize+(osize>>1))
        }
        Assert.assertEquals(getArrayAlloc(arrayM), 27);//0 4 8 12 18 27 40

        for (int i = 0; i < 20; i++) {//remove: shrink from
            arrayM.removeAt(0);//shrink
            // (arraysize+(arraysize>>1)) ->(mHashes.length > (BASE_SIZE*2) && mSize < mHashes.length/3)->osize > (BASE_SIZE*2) ? (osize + (osize>>1)) : (BASE_SIZE*2) ;
            // 4->0;8->0;12->3->8;27->8->8;40->12->18;
            //4,8 减小到0；(arraysize+(arraysize>>1)) 减小到小于1/3，分情况原来大小不大于8，12 18 27 三种情况为8，大于8则(osize + (osize>>1))缩减
            Assert.assertEquals(getArrayAlloc(arrayM), 27);
        }

    }

    private static int getArrayAlloc(ArrayMap list) {
        try {
            Field elementData = null;
            if (list instanceof ArrayMap)
                elementData = SimpleArrayMap.class.getDeclaredField("mHashes");
            elementData.setAccessible(true);
            int[] objects = (int[]) elementData.get(list);
            return objects.length;
        } catch (Exception e) {//throws NoSuchFieldException, IllegalAccessException
            return -1;
        }
    }
}
