package edu.ptu.javatest._20_ooad._21_create;
////-XX:+TraceClassLoading ，类加载安全机制，类加载时间不知，可能造成内存浪费
public class SingleInstance {
    public static  SingleInstance singleinstance=new SingleInstance();
    static {
        System.out.println("SingleInstance  load=================");
    }
    public static SingleInstance getSingleinstance() {
        return singleinstance;
    }
    private SingleInstance(){}
}
