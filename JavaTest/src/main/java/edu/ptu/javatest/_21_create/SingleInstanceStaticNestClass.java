package edu.ptu.javatest._21_create;
////-XX:+TraceClassLoading
public class SingleInstanceStaticNestClass {

//volatile 变量值内存共享
    public static SingleInstanceStaticNestClass getSingleinstance() {
        return StaticNestClass.singleinstance;
    }
    private SingleInstanceStaticNestClass(){}
    public static class StaticNestClass {
        static {
            System.out.println("StaticNestClass load=================");
        }
        private static SingleInstanceStaticNestClass singleinstance=new SingleInstanceStaticNestClass();
    }
}
