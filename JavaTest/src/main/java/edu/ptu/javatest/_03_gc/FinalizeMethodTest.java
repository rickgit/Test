package edu.ptu.javatest._03_gc;

import org.junit.Assert;
import org.junit.Test;

public class FinalizeMethodTest {
    static FinalizeMethodTest obj;
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用 finalize ");
        obj=this;
    }
    @Test
    public void testFinalizeAlivable(){
        obj = new FinalizeMethodTest();
        obj=null;
        System.gc();//触发 finalize方法，只能触发一次
        try {
            Thread.sleep(3000);//  finalize方法优先级低
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         Assert.assertNotNull(obj);

        obj=null;
        System.gc();//触发 finalize方法，只能触发一次
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertNull(obj);
    }

}
