package edu.ptu.test;

import android.os.SystemClock;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SparseArrayCompat;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    //    @Test
    public void testName() throws Exception {

    }

    //    @Test
    public void createAndSearchArrayMap() throws Exception {

        ArrayMap arrayMap = new ArrayMap(50);
        for (int index = 0; index < 50; index++) {
            arrayMap.put(index, "index" + index);
        }
        Random random = new Random();
        long now = System.currentTimeMillis();
        for (int index = 0; index < 1_000_000; index++) {
            arrayMap.get(random.nextInt(50));
        }//47
        System.out.println("createAndSearchSparseArrayCompat===>" + (System.currentTimeMillis() - now));
    }

    //    @Test
    public void createAndSearchSparseArrayCompat() throws Exception {

        SparseArrayCompat sparseArrayCompat = new SparseArrayCompat(50);
        for (int index = 0; index < sparseArrayCompat.size(); index++) {
            sparseArrayCompat.put(index, "index" + index);
        }
        Random random = new Random();
        long now = System.currentTimeMillis();
        for (int index = 0; index < 1_000_000; index++) {
            sparseArrayCompat.get(random.nextInt(50));
        }

        System.out.println("createAndSearchSparseArrayCompat===>" + (System.currentTimeMillis() - now));
    }

    /**
     * 测试加法和减法的效率，javap -v Class.class 看bytecode
     */
    @Test
    public void createTestIncreateDecreate() {
        long l = System.currentTimeMillis();
        for (long index = 0; index < 100_000_000_000l; index++) {

        }
        System.out.println("===>" + (System.currentTimeMillis() - l));
        l = System.currentTimeMillis();
        for (long index = 100_000_000_000l; index > 0; index--) {

        }
        System.out.println("===>" + (System.currentTimeMillis() - l));
    }
}