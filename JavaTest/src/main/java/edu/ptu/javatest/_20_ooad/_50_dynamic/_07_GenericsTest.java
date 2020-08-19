package edu.ptu.javatest._20_ooad._50_dynamic;


import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

//强转没有判断可能强转出错，泛型编译时验证；运行时擦除，避免创建更多类，消耗性能
//类型参数化
//1. 泛型类和泛型接口
//2. 泛型方法
//3. 通配符 ？
//4. 类型擦除
//5. 泛型与数组
//6. 泛型与反射
public class _07_GenericsTest {

    @Test
    public void testExtends() {
        String[] strings = new String[1];
        Assert.assertTrue(strings instanceof Object[]);

        ArrayList<String> list = new ArrayList<>();
        Assert.assertTrue(list instanceof ArrayList);
        Assert.assertTrue(list instanceof List);
//        Assert.assertTrue(list instanceof ArrayList<Object>);//报错
        ArrayList<String>[] listArr = new ArrayList[1];
        Assert.assertTrue(listArr instanceof ArrayList[]);//注意赋值
    }

    //泛型类
    public static class GenericClass<T> {
        public void printType(T t) {
            System.out.println(t);
        }
    }

    @Test
    public void testGenericClass() {
        GenericClass<String> stringGenericClass = new GenericClass<>();
        GenericClass<Integer> intGenericClass = new GenericClass<>();
        Assert.assertEquals(stringGenericClass.getClass(), intGenericClass.getClass());
    }

    //泛型类子类。1，子类是泛型，必须和父类泛型一致。2. 子类不是泛型，父类泛型必须声明泛型的具体类型，默认是Object
    public static class GenericClassChild<T> extends GenericClass<T> {
    }

    public static class GenericClassChildNoParam extends GenericClass<String> {
        @Override
        public void printType(String s) {
            super.printType(s);
        }
    }

    public static class GenericClassChildNoParamDefault extends GenericClass {
        @Override
        public void printType(Object o) {
            super.printType(o);
        }
    }

    //泛型接口
    public interface GenericsInterface<T> {
        public T printGenericsInterface();
    }

    //泛型接口。1. 子类是泛型类，要定义相同的泛型标识；2 子类不是泛型类，默认泛型是使用Object；
    public class GenericsInterfaceChild<T> implements GenericsInterface<T> {

        @Override
        public T printGenericsInterface() {
            return null;
        }
    }

    public class GenericsInterfaceChildDefault implements GenericsInterface {
        @Override
        public Object printGenericsInterface() {
            return new Object();
        }
    }

    //泛型方法。public和方法体之间包含泛型列表（<T>）。调用的时候指定具体类型
    //2. 支持静态方法
    public static class GenericsMethod {
        public <T> T next(T t) {
            return t;
        }

        public static <T> T staticnext(T t) {
            return t;
        }
    }

    @Test
    public void testGenericsMethod() {
        GenericsMethod genericsMethod = new GenericsMethod();
        genericsMethod.next(new String());
    }

    //通配符。extends 上限，约束具体类型必须是某个类及其子类，默认Object。supper 形参下限制，必须是类及其父类
    public class GenericsWildcards {
        public void next(List<?> lists) {

        }

        public void showKeyValueExtends(List<? extends Number> obj) {
        }

        public void showKeyValueSupper(List<? super Number> obj) {
        }
    }

    @Test
    public void testWildcards() {
        GenericsWildcards genericsWildcards = new GenericsWildcards();
        genericsWildcards.showKeyValueExtends(new ArrayList<Number>());
        genericsWildcards.showKeyValueExtends(new ArrayList<Integer>());
        genericsWildcards.showKeyValueSupper(new ArrayList<Number>());
        genericsWildcards.showKeyValueSupper(new ArrayList<Serializable>());
        genericsWildcards.showKeyValueSupper(new ArrayList<Object>());

    }


    @Test
    public void testAdapterMethod() {
        Method[] declaredMethods = null;
        //桥接方法
        declaredMethods = GenericClassChildNoParam.class.getDeclaredMethods();
        Assert.assertEquals(declaredMethods.length, 2);
        //public void edu.ptu.javatest._20_ooad._50_ref.GenericsTest$GenericClassChildNoParam.printType(java.lang.String)
        //public void edu.ptu.javatest._20_ooad._50_ref.GenericsTest$GenericClassChildNoParam.printType(java.lang.Object)
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.println(declaredMethods[i].toString());
            System.out.println(declaredMethods[i].getReturnType());

        }
    }

    public static class Arr<T> {
        T[] t;

        Arr(Class<T> tClass) {
            t = (T[]) Array.newInstance(tClass, 2);
        }
    }

    @Test
    public void testGenericsList() {
        //泛型数组需要声明具体类型，不然会引用错误
        //不允许创建有泛型的数组,使用Array创建
        Arr<String> stringArr = new Arr<String>(String.class);

    }

    @Test
    public void testReflection() throws Exception {
        Class  objectClass = GenericClass.class;
        GenericClass genericClass = (GenericClass) objectClass.newInstance();

        //使用泛型不用强制转化
        Class<GenericClass> objectClass2 = GenericClass.class;
        GenericClass genericClass2 = objectClass2.newInstance();

        //安全及避免强制转化

        //参数化类型
        //1. ParameterizedType
        Type parameterizedType = GenericClassChildNoParam.class.getGenericSuperclass();
        if (parameterizedType instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType) parameterizedType).getActualTypeArguments();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                System.out.println("parameterizedType actualTypeArguments"+actualTypeArguments[i]);
            }
        }
        //2. TypeVariable
        Type next = GenericsMethod.class.getDeclaredMethod("next", Object.class).getGenericReturnType();
          if (next instanceof TypeVariable){
              TypeVariable next1 = (TypeVariable) next;
              System.out.println(next1.getBounds()[0]);
          }

//        Type genericReturnType = staticnext.getGenericReturnType();
        //3. WildcardType
        Method next1 = GenericsWildcards.class.getDeclaredMethod("next", List.class);
        Type[] genericParameterTypes = next1.getGenericParameterTypes();
        WildcardType actualTypeArgument = (WildcardType) ((ParameterizedType) next1.getGenericParameterTypes()[0]).getActualTypeArguments()[0];
        System.out.printf("泛型 "+actualTypeArgument.getUpperBounds()[0]);
        //4. GenericArrayType
        Type t = Arr.class.getDeclaredField("t").getClass().getGenericSuperclass();
        GenericArrayType gat = (GenericArrayType) Arr.class.getDeclaredField("t").getGenericType();
    }


}
