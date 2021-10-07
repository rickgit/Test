package edu.ptu.androidtest._20_dsa;

import android.util.SparseArray;

import androidx.collection.SparseArrayCompat;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class _01_SparseArrayCompatTest {


    @Test
    public void testArrayUtils() {
//        GrowingArrayUtils.insert(mKeys, mSize, i, key);
        try {
            Class<?> aClass = Class.forName("androidx.collection.ContainerHelpers");
            //(T[] array, int currentSize, int index, T element)
            Method insert = aClass.getMethod("idealIntArraySize", int.class);
            //查找n>=4, cap*4 <= 2^n-12，即cap<=2^(n-2)-3 时，返回 cap<=2^(n-2)-3，即找2次方-3,从1开始找；
            // 即n>=4,cap<=2^(n-2)-3，n的最小值时，返回2^(n-2)-3

            insert.setAccessible(true);
            int invoke = (int) insert.invoke(aClass, 14);//1-1;2 -5;6 -13;14-29
            Assert.assertEquals( invoke, 29);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    @Test
    public void testSparseArray() {

        SparseArrayCompat<Object> sparseArray = new SparseArrayCompat<>();// 默认传10 , 转化idea length 为13
        Assert.assertEquals(sparseArray.size(), 0);
        Assert.assertEquals(getArrayAlloc(sparseArray), 13);

        sparseArray = new SparseArrayCompat<>(2);
        Assert.assertEquals(getArrayAlloc(sparseArray), 5);


    }

    @Test
    public void testSparseArraySet() {
        SparseArrayCompat<Object> sparseArray = new SparseArrayCompat<>(0);

        Assert.assertEquals(sparseArray.size(), 0);
        Assert.assertEquals(getArrayAlloc(sparseArray), 0);

        sparseArray = new SparseArrayCompat<>(1);
        Assert.assertEquals(sparseArray.size(), 0);
        Assert.assertEquals(getArrayAlloc(sparseArray), 1);

    }


    @Test
    public void testSparseArrayGC() {
        //put size  keyAt valueAt setValueAt indexOfKey indexOfValue indexOfValueByValue append
        //remove removeAtdelete  标记，不gc
        SparseArrayCompat<Object> sparseArray = new SparseArrayCompat<>();// ArrayUtils.newUnpaddedObjectArray 偶数会变成奇数
        for (int i = 1; i < 10; i++) {
            sparseArray.put(i, i);
        }//i>=0才会gc

        sparseArray.removeAt(0);
        sparseArray.size();//i>=0才会gc


    }


    private static int getArrayAlloc(SparseArrayCompat list) {
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
