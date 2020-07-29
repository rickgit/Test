package edu.ptu.javatest._20_ooad._50_dynamic;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class _05_ProxyTest {
    public static interface MProxy {
        public String getUser();
    }

    @Test
    public void testProxy() {
//        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        try {
            Class<?> proxyGenerator = Class.forName("sun.misc.ProxyGenerator");

            Field declaredField = proxyGenerator.getDeclaredField("saveGeneratedFiles");
            declaredField.setAccessible(true);
            Field modifiers = declaredField.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(declaredField, declaredField.getModifiers() & ~Modifier.FINAL);
            declaredField.set(proxyGenerator, true);
            System.out.println();
            //生成路径 ：JavaTest\com\sun\proxy\$Proxy4.class
            MProxy o = (MProxy) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{MProxy.class}, new InvocationHandler() {

                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    return null;
                }
            });
            o.getUser();
            System.out.println("");

            //还原数据
            modifiers.setInt(declaredField, declaredField.getModifiers() & ~Modifier.FINAL);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new _05_ProxyTest().testProxy();
        System.out.println();
    }
}
