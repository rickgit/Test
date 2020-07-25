package edu.ptu.javatest._20_ooad._50_dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class _03_AnotationTest {

    public static class A{
        @Override
        @Deprecated
        @SuppressWarnings("all")
        public String toString() {//3个内置注解
            return super.toString();
        }

    }
    @Test
    public void testPaseAnnotation() throws Exception {
        Method toString = A.class.getDeclaredMethod("toString");
        Annotation[] annotations = toString.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a);
        }
    }

    //四个 meta-annotation类型
    @Target({ElementType.METHOD,ElementType.TYPE})//注解的位置
//    FIELD, METHOD, PARAMETER, CONSTRUCTOR,  LOCAL_VARIABLE,  ANNOTATION_TYPE,
//    PACKAGE, TYPE_PARAMETER,d  TYPE_USE;
    @Retention(RetentionPolicy.RUNTIME)//注解的声明周期（Source<Class<runtime）
    @Documented//注解被包含在javadoc中
    @Inherited//子类可以继承父类中的该注解
    public @interface Anno{
        String value() default "type";
    }
    @Anno
    public static class AnnTarget{
        @Anno(value="method")
        public void targetMethod(){

        }
    }
    @Test
    //annotation processing tools 编译时增强，生成代码
    public void testAnnoDefin() throws  Exception {
        Method testAnnoDefin = AnnTarget.class.getMethod("targetMethod");
        Annotation[] annotations = testAnnoDefin.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }
        Annotation[] declaredAnnotations = testAnnoDefin.getDeclaredAnnotations();//不包括父类中被Inherited修饰的注解
        for (int i = 0; i < declaredAnnotations.length; i++) {
            System.out.println(declaredAnnotations[i]);
        }
        Assert.assertTrue(testAnnoDefin.isAnnotationPresent(Anno.class));
        if (testAnnoDefin.isAnnotationPresent(Anno.class)){
            Anno annotation = testAnnoDefin.getAnnotation(Anno.class);
            Assert.assertEquals(annotation.value(),"method");
        }
    }


}
