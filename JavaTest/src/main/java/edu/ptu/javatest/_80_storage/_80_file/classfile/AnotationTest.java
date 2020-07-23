package edu.ptu.javatest._80_storage._80_file.classfile;

import org.junit.Test;

import java.lang.reflect.AnnotatedType;

public class AnotationTest {
    @Deprecated
    public static class A{

    }
    @Test
    public void testPaseAnnotation(){
        AnnotatedType[] annotatedInterfaces = A.class.getAnnotatedInterfaces();
    }
}
