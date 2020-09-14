package edu.ptu.javatest._80_storage._70_cache._01_jvm._00_classloader;

import org.junit.Assert;
import org.junit.Test;

public class DynamicLinkTest {
    public static class LinkParent{
        @Override
        public String toString() {
            System.out.println("LinkParent");
            return "p";
        }
    }
    public static class LinkChild extends LinkParent{
        public void print(LinkChild c){
            System.out.println("LinkChild");
        }
        public void print(LinkParent c){
            System.out.println("LinkParent");
        }
        @Override
        public String toString() {System.out.println("LinkChild");
            return "c";
        }
    }
    @Test
    public void test(){
        LinkChild c=new LinkChild();
        Assert.assertEquals(c.toString(),"c");
        c.print(c);
        LinkParent p = new LinkChild();
        Assert.assertEquals(p.toString(),"c");
        c.print(p);
    }
}
