package edu.ptu.androidtest._20_dsa;

import android.util.SparseArray;

import androidx.collection.ArrayMap;
import androidx.collection.SparseArrayCompat;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class _00_SparseArrayTest {
    @Test
    public void testSparseArray() {

        SparseArray<Object> sparseArray = new SparseArray<>();//默认 ：ArrayUtils.newUnpaddedObjectArray(10)=11
        Assert.assertEquals(sparseArray.size(), 0);
        Assert.assertEquals(getArrayAlloc(sparseArray), 11);


    }

    @Test
    public void testSparseArraySet() {
        SparseArray<Object> sparseArray = new SparseArray<>(0);

        Assert.assertEquals(sparseArray.size(), 0);
        Assert.assertEquals(getArrayAlloc(sparseArray), 0);

        sparseArray = new SparseArray<>(1);
        Assert.assertEquals(sparseArray.size(), 0);
        Assert.assertEquals(getArrayAlloc(sparseArray), 1);

    }

    @Test
    public void testSparseArrayPut() {
        int initCap = 1;
        SparseArray<Object> sparseArray = new SparseArray<>(initCap);// ArrayUtils.newUnpaddedObjectArray 偶数会变成奇数
        Assert.assertEquals(getArrayAlloc(sparseArray), (initCap > 0 && initCap % 2 == 0) ? (initCap + 1) : initCap);
        initCap = getArrayAlloc(sparseArray);// ArrayUtils.newUnpaddedObjectArray 偶数会变成奇数
        for (int i = 0; i < initCap + 1; i++) {
            sparseArray.put(i, 1);
        }
        if (initCap == 0 || initCap == 1 || initCap == 3)
            Assert.assertEquals(getArrayAlloc(sparseArray), 9);//GrowingArrayUtils.insert：0,1,3 扩容到9；以后奇数 x2+1
        else
            Assert.assertEquals(getArrayAlloc(sparseArray), (initCap * 2 + 1));//


        initCap = 10;
        sparseArray = new SparseArray<>();//默认设置了10；ArrayUtils.newUnpaddedObjectArray 偶数会变成奇数
        initCap = getArrayAlloc(sparseArray);// ArrayUtils.newUnpaddedObjectArray 偶数会变成奇数
        for (int i = 0; i < initCap + 1; i++) {
            sparseArray.put(i, 1);
        }
        Assert.assertEquals(getArrayAlloc(sparseArray), (initCap * 2 + 1));//
    }

    @Test
    public void testSparseArrayGC() {
        //put size  keyAt valueAt setValueAt indexOfKey indexOfValue indexOfValueByValue append
        //remove removeAtdelete  标记，不gc
        SparseArray<Object> sparseArray = new SparseArray<>();// ArrayUtils.newUnpaddedObjectArray 偶数会变成奇数
        for (int i = 1; i < 10; i++) {
            sparseArray.put(i, i);
        }//i>=0才会gc

        sparseArray.removeAt(0);
        sparseArray.size();//i>=0才会gc


    }

    @Test
    public void testArrayUtils() {
//        GrowingArrayUtils.insert(mKeys, mSize, i, key);
        try {
            Class<?> aClass = Class.forName("com.android.internal.util.ArrayUtils");
            //(T[] array, int currentSize, int index, T element)
            Method insert = aClass.getMethod("newUnpaddedObjectArray", int.class);
            insert.setAccessible(true);
            Object invoke = insert.invoke(aClass, 2);
            Assert.assertEquals((((Object[]) invoke).length), 2 + 1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGrowingArrayUtils() {
//        GrowingArrayUtils.insert(mKeys, mSize, i, key);
        try {
            Class<?> aClass = Class.forName("com.android.internal.util.GrowingArrayUtils");
            //(T[] array, int currentSize, int index, T element)
            Method insert = aClass.getMethod("insert", int[].class, int.class, int.class, int.class);
            insert.setAccessible(true);
            Object invoke = insert.invoke(aClass, new int[0], 0, 0, 1);
            Assert.assertEquals((((int[]) invoke).length), 9);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    private static int getArrayAlloc(SparseArray list) {
        try {
            Field elementData = null;
            elementData = list.getClass().getDeclaredField("mValues");
            elementData.setAccessible(true);
            Object[] objects = (Object[]) elementData.get(list);
            return objects.length;
        } catch (Exception e) {//throws NoSuchFieldException, IllegalAccessException
            return -1;
        }
    }
}
