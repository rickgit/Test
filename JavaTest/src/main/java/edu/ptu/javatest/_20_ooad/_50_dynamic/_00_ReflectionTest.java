package edu.ptu.javatest._20_ooad._50_dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import edu.ptu.javatest._80_storage._80_file._01_classfile2.ObjDiffPack;

public class _00_ReflectionTest {
    @Test
    public void getRefFieldObj() {
        Class<Integer> integerClass = int.class;
        Class<Deprecated> deprecatedClass = Deprecated.class;
        System.out.println("注解" + deprecatedClass);
        Class<Class> classClass = Class.class;
        Class<int[]> aClass = int[].class;
        System.out.println("数组" + aClass);

        Class<? extends ElementType> aClass1 = ElementType.FIELD.getClass();//枚举
        System.out.println("枚举" + aClass1);
        Class<ArrayList> arrayListClass = ArrayList.class;//泛型
        System.out.println("泛型类" + arrayListClass);


    }

    public static Object getRefFieldObj(Object object, Class clazz, String name) {
        try {
            Field declaredField = clazz.getDeclaredField(name);
            declaredField.setAccessible(true);
            return declaredField.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeMethod(Object object, Class clazz, String name,  Class[] type, Object[] params) {
        try {
            Method declaredField = type != null ? clazz.getDeclaredMethod(name, type) : clazz.getDeclaredMethod(name);
            declaredField.setAccessible(true);
            return  type != null ? declaredField.invoke(object, params):declaredField.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getRefFieldBool(Object object, Class clazz, String name) {
        try {
            Field declaredField = clazz.getDeclaredField(name);
            declaredField.setAccessible(true);
            return (boolean) declaredField.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getRefFieldInt(Object object, Class clazz, String name) {
        try {
            Field declaredField = clazz.getDeclaredField(name);
            declaredField.setAccessible(true);
            return (int) declaredField.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static class Obj {
        private static int pristat;
        protected static int prostat;
        static int packstat;
        public final static int pubstat = 0;

        private int pri;
        protected int pro;
        int pack;
        public final int pub = 0;

        public int getPri() {
            return pri;
        }

        public int getPro() {
            return pro;
        }

        public int getPack() {
            return pack;
        }

        public int getPub() {
            return pub;
        }

        public static int getPristat() {
            return pristat;
        }

        public static int getProstat() {
            return prostat;
        }

        public static int getPackstat() {
            return packstat;
        }

        public static int getPubstat() {
            return pubstat;
        }
    }

    public static class ObjChild extends Obj {
        private static int cpristat;
        protected static int cprostat;
        static int cpackstat;
        public static int cpubstat;

        private int cpri;
        protected int cpro;
        int cpack;
        public int cpub;

        public int getCPri() {
            return cpri;
        }

        public int getCPro() {
            return cpro;
        }

        public int getCPack() {
            return cpack;
        }

        public int getCPub() {
            return cpub;
        }

        public static int getCPristat() {
            return cpristat;
        }

        public static int getCProstat() {
            return cprostat;
        }

        public static int getCPackstat() {
            return cpackstat;
        }

        public static int getCPubstat() {
            return cpubstat;
        }
    }

    @Test
    public void testAcceClass() {
        //getFields 获取public 成员，包括父类。面向用户可以调用，不适合用开发场景
        //getDeclaredFields 获取当前类的属性，父类用 getSuperclass()
        Obj obj = new Obj();
        Assert.assertEquals(obj.getClass().getDeclaredFields().length, 8);
        Assert.assertEquals(obj.getClass().getDeclaredMethods().length, 8);
        Assert.assertNotEquals(obj.getClass().getMethods().length, 8);//包括object的方法
        System.out.printf("");
    }

    @Test
    public void testAcceChildClass() {
        //getFields 获取public 成员，包括父类。面向用户可以调用，不适合用开发场景
        //getDeclaredFields 获取当前类的属性，父类用 getSuperclass()
        Obj obj = new ObjChild();
        Assert.assertEquals(obj.getClass().getDeclaredFields().length, 8);
        Assert.assertEquals(obj.getClass().getDeclaredMethods().length, 8);
        Assert.assertNotEquals(obj.getClass().getMethods().length, 8);//包括object的方法
        System.out.printf("");//反射使用动态对象
    }

    @Test
    public void testAccessibleFinalMember() {//
        //getFields 获取public 成员，包括父类。面向用户可以调用，不适合用开发场景
        //getDeclaredFields 获取当前类的属性，父类用 getSuperclass()
        Obj obj = new Obj();
        try {
            Field pub = obj.getClass().getDeclaredField("pub");//final 需要需要修饰，set情况需要加
            int modifiers = pub.getModifiers();
            pub.setAccessible(true);
            pub.set(obj, 1);
            try {
                Field staticpub = obj.getClass().getDeclaredField("pubstat");//final static 不能被修改
                staticpub.setAccessible(true);
                staticpub.set(obj.getClass(), 1);
                Assert.fail("不能执行到这");
            } catch (Exception e) {
                Assert.assertEquals(e.getClass(), IllegalAccessException.class);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        System.out.printf("");
    }

    @Test
    public void testAccessiblePrivateMember() {//
        //getFields 获取public 成员，包括父类。面向用户可以调用，不适合用开发场景
        //getDeclaredFields 获取当前类的属性，父类用 getSuperclass()
        Obj obj = new Obj();

        try {
            Field pri = obj.getClass().getDeclaredField("pri");
            pri.setAccessible(true);
            Object prio = pri.get(obj);

            Field pro = obj.getClass().getDeclaredField("pro");
            Object proo = pro.get(obj);
            Assert.assertFalse(pro.isAccessible());

            Field pack = obj.getClass().getDeclaredField("pack");
            Object packo = pack.get(obj);
            Assert.assertFalse(pack.isAccessible());

            Field pub = obj.getClass().getDeclaredField("pub");//final 需要需要修饰，set情况需要加 pub.setAccessible(true);
            Object pubo = pub.get(obj);
            Assert.assertFalse(pub.isAccessible());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.printf("");
    }

    @Test
    public void testAccessibleDiffPackageMember() {//
        //getFields 获取public 成员，包括父类。面向用户可以调用，不适合用开发场景
        //getDeclaredFields 获取当前类的属性，父类用 getSuperclass()
        ObjDiffPack obj = new ObjDiffPack();

        try {
            Field pri = obj.getClass().getDeclaredField("pri");
            pri.setAccessible(true);
            Object prio = pri.get(obj);

            Field pro = obj.getClass().getDeclaredField("pro");
            Assert.assertFalse(pro.isAccessible());
            pro.setAccessible(true);
            Object proo = pro.get(obj);
            Assert.assertTrue(pro.isAccessible());

            Field pack = obj.getClass().getDeclaredField("pack");
            Assert.assertFalse(pack.isAccessible());
            pack.setAccessible(true);
            Object packo = pack.get(obj);
            Assert.assertTrue(pack.isAccessible());

            Field pub = obj.getClass().getDeclaredField("pub");
            Object pubo = pub.get(obj);
            Assert.assertFalse(pub.isAccessible());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.printf("");
    }

    @Test
    public void testAccessibleDiffClassMember() {//
        //getFields 获取public 成员，包括父类。面向用户可以调用，不适合用开发场景
        //getDeclaredFields 获取当前类的属性，父类用 getSuperclass()
        ObjDiffPack obj = new ObjDiffPack();

        try {
            Field pri = obj.getClass().getDeclaredField("pri");
            pri.setAccessible(true);
            Object prio = pri.get(obj);

            Field pro = obj.getClass().getDeclaredField("pro");
            Assert.assertFalse(pro.isAccessible());
            pro.setAccessible(true);
            Object proo = pro.get(obj);
            Assert.assertTrue(pro.isAccessible());

            Field pack = obj.getClass().getDeclaredField("pack");
            Assert.assertFalse(pack.isAccessible());
            pack.setAccessible(true);
            Object packo = pack.get(obj);
            Assert.assertTrue(pack.isAccessible());

            Field pub = obj.getClass().getDeclaredField("pub");
            Object pubo = pub.get(obj);
            Assert.assertFalse(pub.isAccessible());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.printf("");
    }

    static final int staticFinalField = 0;

    @Test
    public void testStaticFinal() {
        try {
            Field declaredField = _00_ReflectionTest.class.getDeclaredField("staticFinalField");
            Field modifiers = declaredField.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(declaredField, declaredField.getModifiers() & ~Modifier.FINAL);

            declaredField.setAccessible(true);
            declaredField.set(_00_ReflectionTest.class, 1);
            System.out.println();
            //还原
            modifiers.setInt(declaredField, declaredField.getModifiers() & ~Modifier.FINAL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
