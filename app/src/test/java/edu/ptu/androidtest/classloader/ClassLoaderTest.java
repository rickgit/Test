package edu.ptu.androidtest.classloader;

import org.junit.Assert;
import org.junit.Test;

public class ClassLoaderTest {
    @Test
    public void testClassLoader(){
        try {
            //双亲委派，保证最近加载class不会被后来的替换
            Assert.assertEquals(ClassLoaderTest.class.getClassLoader().getClass(),Class.forName("sun.misc.Launcher$AppClassLoader"));
            Assert.assertEquals(ClassLoaderTest.class.getClassLoader().getParent().getClass(),Class.forName("sun.misc.Launcher$ExtClassLoader"));
            Assert.assertEquals(ClassLoaderTest.class.getClassLoader().getParent().getParent(),null);
            //ClassLoaderTest.class.getClassLoader().getParent().getParent()不允许访问 BootstrapClassLoader
        } catch (ClassNotFoundException e) {
            Assert.fail();
        }

    }
}
