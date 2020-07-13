package edu.ptu.javatest._80_storage._70_cache._01_jvm._00_classloader;

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
            Assert.fail(e.getMessage());
        }

    }
    @Test
    public void testLoadClass(){
        int i=1;
        int j=1;
        j+=i;
    }
    @Test
    public void testString(){

        String ab=new String("a")+new String("b");
//         ab.intern();
        Assert.assertTrue(ab!="ab");
       ab= ab.intern();
        Assert.assertTrue(ab=="ab");
    }
    @Test
    public void testNewString(){

        String a=new String("a") ;
        a.intern();
        Assert.assertTrue(a!="a");//证明new String("a") ;已经在常量池创建了a

    }
}
