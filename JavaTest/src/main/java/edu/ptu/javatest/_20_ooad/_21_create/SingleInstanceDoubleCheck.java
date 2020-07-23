package edu.ptu.javatest._20_ooad._21_create;

public class SingleInstanceDoubleCheck {
    public  static volatile SingleInstanceDoubleCheck singleinstance=null;
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
