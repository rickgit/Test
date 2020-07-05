package edu.ptu.androidtest.performace;

import org.junit.Test;

import java.lang.annotation.Annotation;

/**
 * Created by anshu.wang on 2016/3/25.
 */
public class AnotationTest {
    @Test
    public void testGetAnotation(){

        for (int index = 0; index <JsInterfaceUseAndroid.class.getAnnotations().length; index++) {
            long last=System.nanoTime();
            Annotation annotation = (Annotation) JsInterfaceUseAndroid.class.getAnnotations()[index];
            System.out.println(System.nanoTime()-last+" : "+annotation);
        }
    }
    @Test
    public void testGetStringWithoutAnnotation(){
        long last=System.nanoTime();
        SimpleObject jsInterfaceUseAndroid = new SimpleObject();
        System.out.println(System.nanoTime()-last+" : 对象初始化");
        last=System.nanoTime();
        String msg = jsInterfaceUseAndroid.name();
        System.out.println(System.nanoTime()-last+" : "+msg);
    }
}
