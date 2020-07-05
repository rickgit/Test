package edu.ptu.javatest._oob;

import org.junit.Assert;
import org.junit.Test;

public class ObjectTest {
    @Test
    public void testObject(){

        boolean condition = new Integer(1) == new Integer(1);
        Assert.assertFalse(condition);
    }
}
