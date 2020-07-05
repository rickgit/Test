package edu.ptu.javatest._21_create;

public class SingleInstanceDoubleCheck {
    public volatile static SingleInstanceDoubleCheck singleinstance=null;
//volatile 变量值内存共享
    public static SingleInstanceDoubleCheck getSingleinstance() {
        if (singleinstance==null){
            synchronized (SingleInstanceDoubleCheck.class){
                if (singleinstance==null)
                    singleinstance=new SingleInstanceDoubleCheck();
            }
        }
        return singleinstance;
    }
    private SingleInstanceDoubleCheck(){}
}
